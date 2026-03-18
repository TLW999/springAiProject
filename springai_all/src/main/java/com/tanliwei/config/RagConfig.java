package com.tanliwei.config;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RagConfig {

    @Bean
    VectorStore vectorStore(EmbeddingModel embeddingModel) {
        SimpleVectorStore build = SimpleVectorStore.builder(embeddingModel).build();
        //获取文本内容
        String path = "简历.txt";
        TextReader textReader = new TextReader(path);
        textReader.getCustomMetadata().put("path", path);
        List<Document> documents = textReader.get();

        //段落切分
        TokenTextSplitter splitter =
                new TokenTextSplitter(1200,
                        350, 5,
                        100, true);
        splitter.apply(documents);
        //3 添加
        build.add(documents);
        return build;
    }

}
