package com.aluracursos.literalura.service;

public interface IConverterAPI {
    <T> T getApiData(String json, Class<T> tClass);
}
