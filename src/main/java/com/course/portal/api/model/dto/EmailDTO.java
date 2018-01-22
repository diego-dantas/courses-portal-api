package com.course.portal.api.model.dto;

public class EmailDTO {

    private long _id;
    private ProviderDTO providerId;
    private String email;
    private String password;
    private String port;
    private String hostname;
    private String utilizaSsl;
    private String utilizaCredenciais;
    private boolean status;

    public EmailDTO(){ }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public ProviderDTO getProviderId() {
        return providerId;
    }

    public void setProviderId(ProviderDTO providerId) {
        this.providerId = providerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getUtilizaSsl() {
        return utilizaSsl;
    }

    public void setUtilizaSsl(String utilizaSsl) {
        this.utilizaSsl = utilizaSsl;
    }

    public String getUtilizaCredenciais() {
        return utilizaCredenciais;
    }

    public void setUtilizaCredenciais(String utilizaCredenciais) {
        this.utilizaCredenciais = utilizaCredenciais;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
