package tdtu.edu.project_ghn.controller.service;

import tdtu.edu.project_ghn.entity.StatusReceiver;

public interface OnGetStatusReceiverListener {
    void onSuccess(StatusReceiver statusReceiver);
    void onFailure(String err);
}
