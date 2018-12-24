package com.niit.website.lms.excel.event;

import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.event.OneRowAnalysisFinishEvent;

/**
 * Event center.
 *
 * @author jipengfei
 */
public interface AnalysisEventRegisterCenter {

    /**
     * Append listener
     *
     * @param name     listener name.
     * @param listener Callback method after each row is parsed.
     */
    void appendLister(String name, AnalysisEventListener listener);

    /**
     * Parse one row to notify all event listeners
     *
     * @param event parse event
     */
    void notifyListeners(OneRowAnalysisFinishEvent event);

    /**
     * Clean all listeners.
     */
    void cleanAllListeners();
}
