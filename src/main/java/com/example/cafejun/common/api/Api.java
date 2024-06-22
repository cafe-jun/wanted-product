package com.example.cafejun.common.api;

import com.example.cafejun.common.error.ErrorCodeIfs;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {
    private Result result;

    @Valid
    private T body;

    public static <T> Api<T> OK (T body) {
        var api = new Api<T>();
        api.result = Result.OK();
        api.body = body;
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs) {
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIfs);
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs,Throwable tx) {
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIfs, tx);
        return api;
    }
    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs,String description) {
        var api = new Api<Object>();
        api.result = Result.ERROR(errorCodeIfs, description);
        return api;
    }


}
