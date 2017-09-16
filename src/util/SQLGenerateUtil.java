package util;

import GBOMDemo.entity.BaseVechile;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器1.0 2017/9/16
 * Created by wuyiming on 2017/9/16.
 */
public class SQLGenerateUtil {

    private final static String MYBATIS3_XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">";

    private final static String SET_PREFIX = "<set>";

    private final static String MAPPER_SUFFIX = "</mapper>";
    private final static String SELECT_SUFFIX = "</select>";
    private final static String UPDATE_SUFFIX = "</update>";
    private final static String INSERT_SUFFIX = "</insert>";
    private final static String DELETE_SUFFIX = "</delete>";
    private final static String TRIM_SUFFIX = "</trim>";
    private final static String RESULTMAP_SUFFIX = "</resultMap>";
    private final static String SET_SUFFIX = "</set>";
    private final static String SQL_SUFFIX = "</sql>";

    private final static String SELECT = "select";
    private final static String INSERT = "insert";
    private final static String UPDATE = "update";
    private final static String DELETE = "delete";

    private final static int DIALECT_MYSQL = 0;
    private final static int DIALECT_PGSQL = 1;
    private final static int DIALECT_ORACLE = 2;

    private final static String JAVA_TYPE_STRING = "java.lang.String";
    private final static String JAVA_TYPE_MAP = "java.util.Map";

    private final static String BASE_RESULT_MAP = "BaseResultMap";

    private final static String METHOD_SELECTBYPRIMARYKEY = "selectByPrimaryKey";
    private final static String METHOD_FINDALL = "findAll";
    private final static String METHOD_DELETEBYPRIMARYKEY = "deleteByPrimaryKey";
    private final static String METHOD_DELETEALL = "deleteAll";
    private final static String METHOD_INSERT = "insert";
    private final static String METHOD_INSERTSELECTIVE = "insertSelective";
    private final static String METHOD_UPDATEBYPRIMARYKEYSELECTIVE = "updateByPrimaryKeySelective";
    private final static String METHOD_UPDATEBYPRIMARYKEY = "updateByPrimaryKey";

    private final static Map<String,String> JDBC_MAP = new HashMap<String,String>() {
        {
            put("double","DOUBLE");
            put("Double","DOUBLE");
            put("String","VARCHAR");
            put("Date","TIMESTAMP");
            put("int","INT");
            put("Integer","INT");
        }
    };

    /**
     * 创建mapper映射文件
     * @param clazz
     * @param <T>
     */
    public static <T> void createCommonMapperStatements(Class<T> clazz) {

    }

    /**
     * 创建数据库表sql语句
     * @param clazz
     * @param <T>
     */
    public static <T> void createCommonDBSQLStatements(Class<T> clazz) {

    }

    /**
     * 创建mapper.xml配置文件
     * @param clazz
     * @param dialect
     * @param primaryKey
     * @param tableName
     * @param <T>
     * @throws Exception
     */
    public static <T> void createCommonSQLStatements(Class<T> clazz, int dialect, String primaryKey, String tableName) throws Exception {
        String fullName = clazz.getName();
        List<String> content = new ArrayList<>();
        List<Field> fieldsExcludePrimaryKey = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        Field primaryKeyField = null;
        for (Field field : fields) {
           if (field.getName().equals(primaryKey)){
               primaryKeyField = field;
           } else {
               fieldsExcludePrimaryKey.add(field);
           }
        }
        if (primaryKeyField == null) {
            throw new NoSuchFieldException(primaryKey);
        }

        String mapperTag = createMapperTag(fullName + "Mapper");

        String resultMap = createResultMapTag(BASE_RESULT_MAP,fullName) + "\n"
                + createIdTag(formatCamel2DBfield(primaryKey),primaryKey,JDBC_MAP.get(primaryKeyField.getType().getSimpleName())) + "\n";
        for (Field field: fieldsExcludePrimaryKey) {
            resultMap += createResultTag(formatCamel2DBfield(field.getName()),field.getName(),JDBC_MAP.get(field.getType().getSimpleName())) + "\n";
        }
        resultMap += RESULTMAP_SUFFIX;

        String baseColumnList = createSQLTag("Base_Column_List") + "\n";
        for (Field field: fields) {
            baseColumnList += formatCamel2DBfield(field.getName()) + ",";
        }
        baseColumnList = baseColumnList.substring(0,baseColumnList.length() - 1) + "\n" + SQL_SUFFIX;

        String selectByPrimaryKey = createSqlStatementTag(
                SELECT, METHOD_SELECTBYPRIMARYKEY, JAVA_TYPE_STRING, null, BASE_RESULT_MAP) + "\n"
                + "select \n"
                + "<include refid=\"Base_Column_List\" /> \n"
                + "from " + tableName + "\n"
                + "where " + formatCamel2DBfield(primaryKey)
                + " = " + createFieldAndType(primaryKeyField) + "\n"
                + SELECT_SUFFIX;

        String findAll = createSqlStatementTag(
                SELECT,METHOD_FINDALL,null,null,BASE_RESULT_MAP) + "\n"
                + "select \n"
                + "<include refid=\"Base_Column_List\" /> \n"
                + "from " + tableName + "\n"
                +SELECT_SUFFIX;

        String deleteByPrimaryKey = createSqlStatementTag(
                DELETE,METHOD_DELETEBYPRIMARYKEY, JAVA_TYPE_STRING,null,null) + "\n"
                + "delete from " + tableName + "\n"
                + "where " + formatCamel2DBfield(primaryKey)
                + " = " + createFieldAndType(primaryKeyField) + "\n"
                + DELETE_SUFFIX;

        String deleteAll = createSqlStatementTag(
                DELETE,METHOD_DELETEALL,null,null,null) + "\n"
                + "delete from " + tableName + "\n"
                + DELETE_SUFFIX;

        String insert = createSqlStatementTag(
                INSERT,METHOD_INSERT, fullName,null,null) + "\n"
                + "insert into " + tableName + "\n(";
        for (Field field : fields) {
            insert += formatCamel2DBfield(field.getName()) + ",";
        }
        insert = insert.substring(0,insert.length() - 1) + ")\n"
                + " values " + "(";
        for (Field field : fields) {
            insert += createFieldAndType(field) + ",";
        }
        insert = insert.substring(0,insert.length() - 1) + ")\n"
                + INSERT_SUFFIX;

        String insertSelective = createSqlStatementTag(
                INSERT,METHOD_INSERTSELECTIVE, fullName,null,null) + "\n"
                + "insert into " + tableName + "\n"
                + createTrimTag("(",")",",") +"\n";
        for (Field field : fields) {
            insertSelective += createIfTag(field.getName()) + "\n";
        }
        insertSelective += TRIM_SUFFIX + "\n"
                + createTrimTag("values (",")",",")+"\n";
        for (Field field : fields) {
            insertSelective += createIfDbTag(field) + "\n";
        }
        insertSelective += TRIM_SUFFIX + "\n" + INSERT_SUFFIX;

        String updateByPrimaryKeySelective = createSqlStatementTag(
                UPDATE,METHOD_UPDATEBYPRIMARYKEYSELECTIVE,fullName,null,null) + "\n"
                + "update " + tableName + "\n"
                + SET_PREFIX + "\n";
        for (Field field : fieldsExcludePrimaryKey) {
            updateByPrimaryKeySelective += createIfUpdateTag(field) + "\n";
        }
        updateByPrimaryKeySelective += SET_SUFFIX + "\n"
                + "where " + formatCamel2DBfield(primaryKey)
                + " = " + createFieldAndType(primaryKeyField) + "\n" +UPDATE_SUFFIX;

        String updateByPrimaryKey = createSqlStatementTag(
                UPDATE,METHOD_UPDATEBYPRIMARYKEY,fullName,null,null) + "\n"
                + "update " + tableName + "\n"
                + "set ";
        for (Field field: fieldsExcludePrimaryKey) {
            updateByPrimaryKey += formatCamel2DBfield(field.getName()) + " = " + createFieldAndType(field) + ",\n";
        }
        updateByPrimaryKey = updateByPrimaryKey.substring(0,updateByPrimaryKey.length() - 2)
                + "\nwhere " + primaryKey + "=" + createFieldAndType(primaryKeyField) + "\n"
                + UPDATE_SUFFIX;

        content.add(MYBATIS3_XML_HEAD);
        content.add(mapperTag);
        content.add(resultMap);
        content.add(baseColumnList);
        content.add(selectByPrimaryKey);
        content.add(findAll);
        content.add(deleteByPrimaryKey);
        content.add(deleteAll);
        content.add(insert);
        content.add(insertSelective);
        content.add(updateByPrimaryKeySelective);
        content.add(updateByPrimaryKey);
        content.add(MAPPER_SUFFIX);

        for (String line : content) {
            System.out.println(line);
        }
    }

    private static String createTrimTag(String prefix,String suffix,String suffixOverrides) {
        return "<trim prefix=\"" + prefix + "\" suffix=\"" + suffix + "\" suffixOverrides=\""+suffixOverrides+"\" >";
    }

    private static String createFieldAndType(Field field) {
        return "#{" + field.getName() + ",jdbcType="+ JDBC_MAP.get(field.getType().getSimpleName()) +"}";
    }

    private static String formatCamel2DBfield(String camelField) {
        char[] chars = camelField.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars) {
            if (c <= 'Z' && c >= 'A') {
                stringBuilder.append('_');
                stringBuilder.append((char)(c - ('Z' - 'z')));
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    private static String createSQLTag(String id) {
        return "<sql id=\"" + id + "\" >";
    }

    private static String createIfTag(String field) {
        return "<if test=\"" + field + " != null\" > \n"
                + formatCamel2DBfield(field) + ",\n</if>";
    }

    private static String createIfDbTag(Field field) {
        return "<if test=\"" + field.getName() + " != null\" > \n"
                + createFieldAndType(field) + ",\n</if>";
    }

    private static String createIfUpdateTag(Field field) {
        return "<if test=\"" + field.getName() + " != null\" > \n"
                + formatCamel2DBfield(field.getName()) + " = " + createFieldAndType(field) + ",\n"
                + "</if>";
    }

    private static String createResultMapTag(String id, String type) {
        return "<resultMap id=\"" + id + "\" type=\"" + type + "\" >";
    }

    private static String createIdTag(String column,String property,String jdbcType) {
        return "<id column=\"" + column + "\" property=\"" + property + " \" jdbcType=\"" + jdbcType + "\" />";
    }

    private static String createResultTag(String column,String property,String jdbcType) {
        return "<result column=\"" + column + "\" property=\"" + property +"\" jdbcType=\""+ jdbcType +"\" />";
    }

    private static String createMapperTag(String namespace) {
        return "<mapper namespace=" + "\"" + namespace + "\">";
    }

    private static String createSqlStatementTag(String type,String id,String paramType,String resultType,String resultMap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<" + type + " id=\"" + id + "\" ");
        if (paramType != null) {
            stringBuilder.append(" parameterType=\"" + paramType + "\"");
        }
        if (resultType != null) {
            stringBuilder.append(" resultType=\"" + resultType + "\"");
        }
        if (resultMap != null) {
            stringBuilder.append(" resultMap=\"" + resultMap + "\"");
        }
        stringBuilder.append(" >");
        return stringBuilder.toString();
    }

    private SQLGenerateUtil() {
        throw new AssertionError("not allowed to create an instant!");
    }

    public static void main(String[] args) throws Exception{
        createCommonSQLStatements(BaseVechile.class,DIALECT_MYSQL,"color","base_vehicle");
    }
}