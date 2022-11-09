package com.zn.tdd;

/**
 * @description:
 * @author:zn
 * @company:万息科技
 * @projectName:tdd_learn
 * @page:com.zn.tdd
 * @date:2022/11/2-19:01:27
 */
public class Schema {
    private String commandName;
    private Class typeClass;

    public Schema(String commandName, Class typeClass) {
        this.commandName = commandName;
        this.typeClass = typeClass;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public Class getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(Class typeClass) {
        this.typeClass = typeClass;
    }
}
