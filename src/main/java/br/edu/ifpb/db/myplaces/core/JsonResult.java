package br.edu.ifpb.db.myplaces.core;

/**
 *
 * @author joaomarcos
 */
public class JsonResult {
    private boolean sucess;
    private String msg;       

    public JsonResult(boolean sucess, String msg) {
        this.sucess = sucess;
        this.msg = msg;
    }

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
}
