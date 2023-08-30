package com.romone183.capacitor.printer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.webkit.WebView;

import androidx.annotation.NonNull;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Printer")
public class PrinterPlugin extends Plugin {


    @PluginMethod
    public void check(PluginCall call) {
        String item = call.getString("item");

        boolean printable = canPrintItem(item);

        JSObject result = new JSObject();
        result.put("printable", printable);

        call.resolve(result);
    }

    @PluginMethod
    public void types(PluginCall call) {
        JSArray utis = getPrintableTypes();

        JSObject result = new JSObject();
        result.put("types", utis);

        call.resolve(result);
    }

    @PluginMethod
    public void print(PluginCall call) {
        String content = call.getString("content");
        JSObject settings = call.getObject("settings");

        printDocument(content, settings, call);
    }

    private boolean canPrintItem(String item) {
        // Replace this with logic to check if item can be printed
        return false;
    }

    private JSArray getPrintableTypes() {
        // Replace this with logic to get printable types
        // For example: String[] types = PrintManager.getPrintableTypes();
        // Convert types array to JSArray and return
        return new JSArray();
    }

    private void printDocument(String content, JSObject settings, PluginCall call) {

        PrintManager pm = new PrintManager(this.getContext());
        pm.print(content, settings, this.getBridge().getWebView(), (boolean completed)-> sendPluginResult(call, completed) );


    }
    private void sendPluginResult(@NonNull PluginCall call, boolean value) {

        JSObject result = new JSObject();
        result.put("status", value);

        call.resolve(result);
    }

}
