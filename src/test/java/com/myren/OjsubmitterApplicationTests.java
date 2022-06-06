package com.myren;
import com.myren.submit.HDUSubmitter;
import com.myren.submit.Result;
import com.myren.submit.Submission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OjsubmitterApplicationTests {


    @Autowired
    @Qualifier("hduSubmitter1")
    HDUSubmitter hduSubmitter1;

    @Test
    void contextLoads() throws Exception {
        Submission submission=new Submission();
        submission.setLanguage(0).setOriginProblemId(1000+"").setSourceCode("#include<bits/stdc++.h>\n" +
                "using namespace std;\n" +
                "int main()\n" +
                "{\n" +
                "   long long a,b;\n" +
                "while(cin>>a>>b)\n" +
                "{\n" +
                "cout<<a+b<<endl;\n" +
                "}\n" +
                "}");
        hduSubmitter1.setSubmission(submission);
        hduSubmitter1.work();
        Result result = hduSubmitter1.getResult();
        System.out.println(result);
    }

}
