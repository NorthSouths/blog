package com.myren;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.myren.entity.Blog;
import com.myren.entity.MidMoudle;
import com.myren.entity.Overp;
import com.myren.entity.Problem;
import com.myren.mapper.BlogMapper;
import com.myren.mapper.OverpMapper;
import com.myren.mapper.ProblemMapper;
import com.myren.mapper.SpecialMapper;
import com.myren.service.OverpService;
import com.myren.service.ProblemService;
import com.myren.service.SpecialService;
import com.myren.util.JwtUtils;
import com.myren.util.OSSUtil;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.text.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    SpecialMapper specialMapper;
    @Autowired
    SpecialService specialService;
    @Autowired
    ProblemService problemService;
    @Autowired
    ProblemMapper problemMapper;
    @Autowired
    OverpMapper overpMapper;
    @Autowired
    OverpService overpService;
    @Autowired
    BlogMapper blogMapper;

    @Value("${aliyun.endpoint}")
    private String endpoint;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")

    @Test

    void contextLoads() {
        System.out.println(jwtUtils.generateToken(8L));
        System.out.println(jwtUtils.getClaimByToken(jwtUtils.generateToken(8L)));
    }
    @Test
    void get(){
        System.out.println(overpMapper.rank(1L));
    }
    @Test
    void t(){
        QueryWrapper<Problem>wrapper=new QueryWrapper<>();
        wrapper.eq("sid",1L);
        problemMapper.selectList(wrapper).forEach(System.out::println);
        //problemService.list(wrapper).forEach(System.out::println);
    }
    @Test
    void test(){
        Overp overp = new Overp();
        overp.setId(1L);
        overp.setPid(7L);
        overp.setStatus(-1);
        overpMapper.UpdateByIdandPidtwo(overp.getId(),overp.getPid());
    }
    @Test
    void uploadDocuments(){
        QueryWrapper<Blog>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",1L);
        System.out.println(blogMapper.selectList(queryWrapper));

    }

    @Test
    public void generateAsciiDocs() throws Exception {
        //    输出Ascii格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:8080/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("./docs/asciidoc/generated"));
    }

    /**
     * 生成Markdown格式文档
     * @throws Exception
     */
    @Test
    public void generateMarkdownDocs() throws Exception {
        //    输出Markdown格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:8080/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("./docs/markdown/generated"));
    }
    /**
     * 生成AsciiDocs格式文档,并汇总成一个文件
     * @throws Exception
     */
    @Test
    public void generateAsciiDocsToFile() throws Exception {
        //    输出Ascii到单文件
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:8080/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFile(Paths.get("./docs/asciidoc/generated/all"));
    }

    /**
     * 生成Markdown格式文档,并汇总成一个文件
     * @throws Exception
     */
    @Test
    public void generateMarkdownDocsToFile() throws Exception {
        //    输出Markdown到单文件
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:8080/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFile(Paths.get("./docs/markdown/generated/all"));
    }
}
