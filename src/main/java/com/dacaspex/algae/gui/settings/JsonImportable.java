package com.dacaspex.algae.gui.settings;

import org.json.JSONObject;

public interface JsonImportable {
    public String getIdentification();

    public void importSettings(JSONObject data) throws Exception;
}
