package com.myren.submit;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Submission {
    private int language;
    private String sourceCode;
    private String originProblemId;
}
