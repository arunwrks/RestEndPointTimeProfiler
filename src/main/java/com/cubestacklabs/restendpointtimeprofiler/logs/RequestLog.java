package com.cubestacklabs.restendpointtimeprofiler.logs;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RequestLog {

    private String clazz;

    private String method;

    private String httpMethod;

    private String uri;

    private long processingTime;// milliseconds

    private long contentSize;// bytes

    private String userId;

    private RequestLog() {}

    private RequestLog(String clazz, String method, String httpMethod, String uri, String userId, long processingTime, long contentSize) {
        this.clazz = clazz;
        this.method = method;
        this.httpMethod = httpMethod;
        this.uri = uri;
        this.userId = userId;
        this.processingTime = processingTime;
        this.contentSize = contentSize;
    }

    public static class Builder {

        private String clazz;

        private String method;

        private String httpMethod;

        private String uri;

        private long processingTime;// milli seconds

        private long contentSize;// bytes

        private String userId;

        public Builder clazz(String clazz) {
            this.clazz = clazz;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder httpMethod(String httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        public Builder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder processingTime(long processingTime) {
            this.processingTime = processingTime;
            return this;
        }

        public Builder contentSize(long contentSize) {
            this.contentSize = contentSize;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public RequestLog build() {
            return new RequestLog(clazz, method, httpMethod, uri, userId, processingTime, contentSize);
        }
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(clazz).append(" ")
                .append(method).append(" ")
                .append(userId == null ? "-" : userId).append(" ")
                .append(httpMethod).append(" ")
                .append(uri).append(" ")
                .append(processingTime).append(" ")
                .append(contentSize)
                .toString();
    }

    public String toJson() {
        return toJson(ToStringStyle.JSON_STYLE);
    }

    public String toJson(ToStringStyle style) {
        return new ToStringBuilder(this, style)
                .append("clazz", clazz)
                .append("method", method)
                .append("userId", userId == null ? "-" : userId)
                .append("httpMethod", httpMethod)
                .append("uri", uri)
                .append("processingTime", processingTime)
                .append("contentSize", contentSize).build().toString();
    }
}
