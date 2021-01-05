package com.ssomai.myjavalibraries.java;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.Map;

/**
 * Created by ssomai on 2/15/16.
 */
public class Tool_Json {
    private static void log(String pLog) {
        System.out.println("Tool_Json] "+pLog);
    }


//    public static class StringTrimModule extends SimpleModule {
//        public StringTrimModule() {
//            addDeserializer(String.class, new StdScalarDeserializer<String>(String.class) {
//                @Override
//                public String deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException,
//                        JsonProcessingException {
//                    return jsonParser.getValueAsString().trim();
//                }
//            });
//        }
//    }

    private static ObjectMapper sObjectMapper = null;
    public static ObjectMapper getObjectMapper() {
        if(sObjectMapper == null) {
            sObjectMapper = new ObjectMapper();
//            // 2019.11.25 공개것만
//          // 2020.03.17 이게 왜 주석처리되어있지?
//            sObjectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.PUBLIC_ONLY);

//            sObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//            sObjectMapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
//            sObjectMapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);
//            sObjectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
//            sObjectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
//            sObjectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
//            sObjectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
//            sObjectMapper.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, false);
//            sObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            sObjectMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
            sObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            sObjectMapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
            sObjectMapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);
            sObjectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);

//            sObjectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            sObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
            sObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            sObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            sObjectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
            sObjectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            sObjectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
            sObjectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
            sObjectMapper.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, false);
            sObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            sObjectMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);

//            lg("getObjectMapper ");
            SimpleModule sm = new SimpleModule();
//            sm.addSerializer(new JsonSerializer<Object>() {
//                @Override
//                public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//
//                }
//            });
//            sm.addSerializer(String.class, new JsonSerializer<String>() {
//                @Override
//                public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) {
//                    log("serialize value:"+value+" "+gen.toString()+" "+serializers);
//                }
//            });
            sm.addDeserializer(String.class, new StdScalarDeserializer<String>(String.class) {
                @Override
                public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
                    String str = jp.getValueAsString();
//                    lg("deserialize 1 str:"+str);
                    if(str != null) {
                        return str.trim();
                    }

//                    jp.getValueAsString().trim();
//                    JsonToken ct = jp.getCurrentToken();
//                    lg("deserialize 1 "+ct);
//                    if(ct != null) {
//                        String str = ct.asString();
//                        lg("deserialize 2 "+str);
//                        if(str != null) {
//                            return str.trim();
//                        }
//                    }
                    return null;
                }
            });

            sObjectMapper.registerModule(sm);
        }
        return sObjectMapper;
    }

//    private static ObjectMapper getObjectMapper() {
//        ObjectMapper sObjectMapper = new ObjectMapper();
//        sObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        sObjectMapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
//        sObjectMapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);
//        sObjectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
//        sObjectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
//        sObjectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
//        sObjectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
//        sObjectMapper.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, false);
//        sObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        sObjectMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
//        return sObjectMapper;
//    }

    public static String showObject(Object pObject) throws JsonProcessingException {
        if(pObject == null) {
            return "";
        }
        return getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(pObject);
    }
    public static String serializeJson(Object pObject) throws JsonProcessingException {
        if(pObject == null) {
            return "";
        }
        return getObjectMapper().writer().writeValueAsString(pObject);
    }
//    // TODO
//    public static String serializeJson(Object pObject, String ... pPrefixFilters) throws JsonProcessingException {
//        FilterProvider filters = new SimpleFilterProvider()
//                .addFilter("PrefixFilter", new SimpleBeanPropertyFilter() {
////                    @Override
////                    protected boolean includeElement(Object elementValue) {
////                        return super.includeElement(elementValue);
////                    }
//
//                    @Override
//                    protected boolean include(BeanPropertyWriter writer) {
//                        lg("include "+writer.getName());
//                        return super.include(writer);
//                    }
//
//                    @Override
//                    protected boolean includeElement(Object elementValue) {
//                        lg("includeElement "+elementValue);
//                        return super.includeElement(elementValue);
//                    }
//
//                    @Override
//                    protected boolean include(PropertyWriter writer) {
//                        lg("include "+writer.getName());
//                        return super.include(writer);
//                    }
//
//                    @Override
//                    public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
//                        lg("serializeAsField ");
//                        super.serializeAsField(pojo, jgen, provider, writer);
//                    }
//                });
//        return getObjectMapper().writer(filters).writeValueAsString(pObject);
////                .addFilter("filter properties by name",
////                        SimpleBeanPropertyFilter.serializeAllExcept(
////                                ignorableFieldNames));
//    }
    public static void deserializeJson(Object pObject, String pJson) throws IOException {
        if(pObject == null) {
            return;
        }
        if(Tool_Java.isStringBlank(pJson)) {
            return;
        }
        MappingIterator<Object> ret = getObjectMapper().readerForUpdating(pObject).readValues(pJson);
        while(ret.hasNext()) {
            Object obj = ret.next();
//            lg("updateInstanceToMap 1...1 " + getObjectMapper().writeValueAsString(obj));
        }
    }

    public static <T> T copyByJson(T pObject) throws IOException, IllegalAccessException, InstantiationException {
        if(pObject == null) {
            return null;
        }
        T ret = (T) pObject.getClass().newInstance();
        deserializeJson(ret, serializeJson(pObject));
        return ret;
    }
    //    public static String getFragmentClassName(String pJson) throws IOException {
////        def slurper = new JsonSlurper()
////        def result = slurper.parseText(pJson)
////        return result.FragParam_Class
//
//        JsonNode node = getObjectMapper().reader().readTree(pJson);
//        return node.get(Default_Fragment.FragParam_Class).asText();
//    }

//    public static <T> T deserializeHashMapToObject(T pObject, HashMap pJson) {
//        TypeFactory typeFactory = getObjectMapper().getTypeFactory();
//        MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, pObject.getClass());
//        //getObjectMapper().readval
//        HashMap<String, T> map = getObjectMapper().readValue(pJson, mapType);
//    }

public static Map<String, Object> createInstanceToMap(Object pObject) throws IOException {
    String json = getObjectMapper().writeValueAsString(pObject);
    Map<String, Object> map = getObjectMapper().readValue(json, new TypeReference<Map<String, Object>>() {
    });
    return map;
}

    public static void updateInstanceToMap(Object pObject, Map map) throws IOException {
//        String json = getObjectMapper().writeValueAsString(pInstance);
//        TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};

//        ObjectMapper om = new ObjectMapper();
//        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        om.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
//        om.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);
//        om.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
//        om.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
//        om.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
//        om.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
//        om.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, false);
//        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        om.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);

        String json = getObjectMapper().writeValueAsString(pObject);
//        lg("updateInstanceToMap 1 "+json);
//        lg("updateInstanceToMap 1 "+getObjectMapper().writeValueAsString(map));
        MappingIterator<Object> ret = getObjectMapper().readerForUpdating(map).readValues(json);
        // ?
        while(ret.hasNext()) {
            Object obj = ret.next();
//            lg("updateInstanceToMap 1...1 " + getObjectMapper().writeValueAsString(obj));
        }
//        lg("updateInstanceToMap 2 "+json);
//        lg("updateInstanceToMap 2 "+getObjectMapper().writeValueAsString(map));
    }
    public static void updateMapToInstance(Map map, Object pObject) throws IOException {
        String json = getObjectMapper().writeValueAsString(map);
//        lg("updateMapToInstance 1 "+json);
//        lg("updateMapToInstance 1 "+getObjectMapper().writeValueAsString(pObject));
        MappingIterator<Object> ret = getObjectMapper().readerForUpdating(pObject).readValues(json);
        // ?
        while(ret.hasNext()) {
            Object obj = ret.next();
//            lg("updateMapToInstance 1...1 " + getObjectMapper().writeValueAsString(obj));
        }
//        lg("updateMapToInstance 2 "+json);
//        lg("updateMapToInstance 2 "+getObjectMapper().writeValueAsString(pObject));

//        ObjectMapper om = new ObjectMapper();
//        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        om.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
//        om.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);
//        om.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
//        om.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
//        om.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
//        om.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
//        om.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, false);
//        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        om.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
//        String json;
//        {
//            json = om.writeValueAsString(map);
//            lg("updateMapToInstance 1 " + json);
//            lg("updateMapToInstance 1 " + om.writeValueAsString(pObject));
//        }
//        {
////            ObjectMapper om = new ObjectMapper();
//            MappingIterator<Object> ret = om.readerForUpdating(pObject).readValues(json);
//            while(ret.hasNext()) {
//                Object obj = ret.next();
//                lg("updateMapToInstance 1...1 " + om.writeValueAsString(obj));
//            }
//            lg("updateMapToInstance 2 " + json);
//            lg("updateMapToInstance 2 " + om.writeValueAsString(pObject));
//        }

//        String keyAttribute = null;
//        String setMethodString = "set";
//        String methodString = null;
//        Iterator itr = map.keySet().iterator();
//        while(itr.hasNext()){
//            keyAttribute = (String) itr.next();
//            methodString = setMethodString+keyAttribute.substring(0,1).toUpperCase()+keyAttribute.substring(1);
//            try {
//                Method[] methods = objClass.getClass().getDeclaredMethods();
//                for(int i=0;i<=methods.length-1;i++){
//                    if(methodString.equals(methods[i].getName())){
//                        System.out.println("invoke : "+methodString);
//                        methods[i].invoke(objClass, map.get(keyAttribute));
//                    }
//                }
//            } catch (SecurityException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//        return objClass;
    }

}
