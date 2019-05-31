package com.coding.args;

import java.util.ArrayList;
import java.util.List;

public class Args {


    public static final String ARGS_TAG = "-";


    public static final List<String> SCHEMA_LIST = new ArrayList<String>() {{
        add("printlog l:logging:boolean:True p:port:int:8080 d:directory:string:/usr/logs");
    }};
    public static final String REGEX_SPACE_TAG = " ";

    /**
     * @param cmdstr example:printlog -l True -p 8080 -d /usr/log
     * @return example: logging=True port=8080 directory=/usr/log
     */
    public String parser(String cmdstr) {
        if (!cmdstr.contains(ARGS_TAG)) {
            return "args is not correct args";
        }
        if (!checkSchema(cmdstr)) {
            return "args is not have correct schema";
        }
        return parseCmd(cmdstr);
    }

    private String parseCmd(String cmdstr) {
        String argumentStr = "";
        int index = getSchemaListIndex(cmdstr);
        String schema = getSchema(cmdstr);

        String[] schemaDesc = SCHEMA_LIST.get(index).replace(schema + REGEX_SPACE_TAG, "").split(REGEX_SPACE_TAG);
        String cmd = cmdstr.replace(schema + REGEX_SPACE_TAG, "");
        String[] args = cmd.split(ARGS_TAG);

        for (int i = 0; i < schemaDesc.length; i++) {

            String[] schemaDef = schemaDesc[i].split(":");   //l:logging:boolean:true
            String[] argsValue = args[i+1].split(REGEX_SPACE_TAG); // l
            if (argsValue[0].equals(schemaDef[0])) {  //参数定义匹配 -lif()
                if (argsValue.length > 1) { // have argument value
                    //有值的时候检查类型type argsValue[1]的类型和 schemaDef[2] 相匹配
                    argumentStr += schemaDef[1] + "=" + argsValue[1];  //logging=True
                } else {
                    //无值的时候赋默认值
                    argumentStr += schemaDef[1] + "=" + schemaDef[3];  //logging=True
                }
            }
            argumentStr += " ";
        }
        return argumentStr;
    }

    private boolean checkSchema(String cmdstr) {
        String cmdSchema = getSchema(cmdstr);
        for (String schemaStr : SCHEMA_LIST) {
            if (cmdSchema.equals(getSchema(schemaStr))) {
                return true;
            }
        }
        return false;
    }

    private int getSchemaListIndex(String cmdstr) {
        String cmdSchema = getSchema(cmdstr);
        int index = 0;
        for (String schemaStr : SCHEMA_LIST) {
            if (cmdSchema.equals(getSchema(schemaStr))) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private String getSchema(String schemaStr) {
        return schemaStr.substring(0, schemaStr.indexOf(" "));
    }

}
