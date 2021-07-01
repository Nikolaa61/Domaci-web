package model;

public class Response {

    private Result result;
    private Action akcija;

    public Response() {
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setAkcija(Action akcija){ this.akcija = akcija; }

    public Action getAkcija() {
        return akcija;
    }
}
