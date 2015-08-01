package com.smartbee.spring.tutorial.util;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by TheArtOfMovement on 6/29/15.
 */
public class Pagination<T> {
    private Page<T> deploymentLog;
    private List<T> deploymentData;
    private Integer beginIndex;
    private Integer endIndex;
    private Integer currentIndex;
    private String baseUrl;

    public Page<T> getDeploymentLog() {
        return deploymentLog;
    }

    public void setDeploymentLog(Page<T> deploymentLog) {
        this.deploymentLog = deploymentLog;
    }

    public List<T> getDeploymentData() {
        return deploymentData;
    }

    public void setDeploymentData(List<T> deploymentData) {
        this.deploymentData = deploymentData;
    }

    public Integer getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(Integer beginIndex) {
        this.beginIndex = beginIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public Integer getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(Integer currentIndex) {
        this.currentIndex = currentIndex;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "deploymentLog=" + deploymentLog +
                ", deploymentData=" + deploymentData +
                ", beginIndex=" + beginIndex +
                ", endIndex=" + endIndex +
                ", currentIndex=" + currentIndex +
                ", baseUrl='" + baseUrl + '\'' +
                '}';
    }
}
