package com.niit.service.uploader.path.impl;


import com.niit.service.uploader.path.ChildPath;
import com.niit.service.uploader.util.TimeUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(
        name = "xin.uploader.child-path-strategy", havingValue = "year"
)
public class YearChildPath implements ChildPath {

    @Override
    public String getChildPath() {
        return "/" + TimeUtil.year();
    }
}
