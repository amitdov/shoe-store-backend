package store.graphql;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Component
public class GraphQLService {

    @Autowired
    QueryResolverService queryResolverService;

    private static final String GRAPHQL_URL = "shoeStore.graphqls";

//    @Autowired
//    ResourceLoader resourceLoader;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    /*
     * No cors origin global setting.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("graphql").allowedOrigins("*");
            }
        };
    }

    private GraphQL graphQL;

    // load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {
        URL url = Resources.getResource(GRAPHQL_URL);
        String sdl = Resources.toString(url, Charsets.UTF_8);

        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {

        Map<String, DataFetcher> queryDataFetchers = queryResolverService.initQueryDataFetchers();

        return RuntimeWiring.newRuntimeWiring()
                .type(WiringNames.QUERY_NAME, typeWiring ->
                        typeWiring.dataFetchers(queryDataFetchers))
                .build();
    }
}
