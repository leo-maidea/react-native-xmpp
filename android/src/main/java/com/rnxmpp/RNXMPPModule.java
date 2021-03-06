package com.rnxmpp;

import android.text.TextUtils;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;

import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatManager;

import android.util.Log;

import com.rnxmpp.service.RNXMPPCommunicationBridge;
import com.rnxmpp.service.XmppServiceSmackImpl;

/**
 * Created by Kristian Frølund on 7/19/16.
 * Copyright (c) 2016. Teletronics. All rights reserved
 */
public class RNXMPPModule extends ReactContextBaseJavaModule implements com.rnxmpp.service.XmppService {

    public static final String MODULE_NAME = "RNXMPP";
    XmppServiceSmackImpl xmppService;

    public RNXMPPModule(ReactApplicationContext reactContext) {
        super(reactContext);
        xmppService = new XmppServiceSmackImpl(new RNXMPPCommunicationBridge(reactContext));
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @Override
    @ReactMethod
    public void trustHosts(ReadableArray trustedHosts) {
        this.xmppService.trustHosts(trustedHosts);
    }

    @Override
    @ReactMethod
    public void connect(String jid, String password, String authMethod, String hostname, Integer port){
        this.xmppService.connect(jid, password, authMethod, hostname, port);
    }

    @ReactMethod
    public void joinRoom(String mucJid, String userNickname, String timestamp) {
        if(TextUtils.isEmpty(userNickname)){
          Log.w("react-native-xmpp", "Cant join room, nickname empty");
        }
        this.xmppService.joinRoom(mucJid, userNickname, timestamp);
    }

    @ReactMethod
    public void sendRoomMessage(String mucJid, String text, String subject, String messageId) {
        this.xmppService.sendRoomMessage(mucJid, text, subject, messageId);
    }

    @ReactMethod
    public void sendRoomMessageUpdated(String mucJid, String text, String subject, String messageId) {
        this.xmppService.sendRoomMessageUpdated(mucJid, text, subject, messageId);
    }

    @ReactMethod
    public void leaveRoom(String mucJid) {
        this.xmppService.leaveRoom(mucJid);
    }

    @Override
    @ReactMethod
    public void message(String text, String to, String thread){
        this.xmppService.message(text, to, thread);
    }

    @Override
    @ReactMethod
    public void messageUpdated(String text, String to, String thread, String messageId) {
        this.xmppService.messageUpdated(text, to, thread,messageId);
    }



    @Override
    @ReactMethod
    public void presence(String to, String type) {
        this.xmppService.presence(to, type);
    }

    @Override
    @ReactMethod
    public void removeRoster(String to) {
        this.xmppService.removeRoster(to);
    }

    @Override
    @ReactMethod
    public void disconnect() {
        this.xmppService.disconnect();
    }

    @Override
    @ReactMethod
    public void fetchRoster() {
        this.xmppService.fetchRoster();
    }

    @Override
    @ReactMethod
    public void sendStanza(String stanza) {
        this.xmppService.sendStanza(stanza);
    }

    @Override
    @ReactMethod
    public void createRoasterEntry(String jabberId, String name) {
        this.xmppService.createRoasterEntry(jabberId,name);
    }

    @Override
    @ReactMethod
    public void sendComposingState(String to, String thread,String state) {
        this.xmppService.sendComposingState(to,thread,state);
    }

    @Override
    @ReactMethod
    public void requestMessageId() {
      this.xmppService.requestMessageId();
    }


}
