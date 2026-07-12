package com.sun.tools.javac.api;

import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;
import com.sun.tools.javac.util.Context;
import java.util.Arrays;
import java.util.Collection;

/* loaded from: target.jar:com/sun/tools/javac/api/MultiTaskListener.class */
public class MultiTaskListener implements TaskListener {
    public static final Context.Key<MultiTaskListener> taskListenerKey = new Context.Key<>();
    TaskListener[] listeners = new TaskListener[0];
    ClientCodeWrapper ccw;

    public static MultiTaskListener instance(Context context) {
        MultiTaskListener multiTaskListener = (MultiTaskListener) context.get(taskListenerKey);
        if (multiTaskListener == null) {
            multiTaskListener = new MultiTaskListener(context);
        }
        return multiTaskListener;
    }

    protected MultiTaskListener(Context context) {
        context.put((Context.Key<Context.Key<MultiTaskListener>>) taskListenerKey, (Context.Key<MultiTaskListener>) this);
        this.ccw = ClientCodeWrapper.instance(context);
    }

    public Collection<TaskListener> getTaskListeners() {
        return Arrays.asList(this.listeners);
    }

    public boolean isEmpty() {
        return this.listeners.length == 0;
    }

    public void add(TaskListener taskListener) {
        for (TaskListener taskListener2 : this.listeners) {
            if (this.ccw.unwrap(taskListener2) == taskListener) {
                throw new IllegalStateException();
            }
        }
        this.listeners = (TaskListener[]) Arrays.copyOf(this.listeners, this.listeners.length + 1);
        this.listeners[this.listeners.length - 1] = this.ccw.wrap(taskListener);
    }

    public void remove(TaskListener taskListener) {
        for (int i = 0; i < this.listeners.length; i++) {
            if (this.ccw.unwrap(this.listeners[i]) == taskListener) {
                TaskListener[] taskListenerArr = new TaskListener[this.listeners.length - 1];
                System.arraycopy(this.listeners, 0, taskListenerArr, 0, i);
                System.arraycopy(this.listeners, i + 1, taskListenerArr, i, taskListenerArr.length - i);
                this.listeners = taskListenerArr;
                return;
            }
        }
    }

    @Override // com.sun.source.util.TaskListener
    public void started(TaskEvent taskEvent) {
        for (TaskListener taskListener : this.listeners) {
            taskListener.started(taskEvent);
        }
    }

    @Override // com.sun.source.util.TaskListener
    public void finished(TaskEvent taskEvent) {
        for (TaskListener taskListener : this.listeners) {
            taskListener.finished(taskEvent);
        }
    }

    public String toString() {
        return Arrays.toString(this.listeners);
    }
}
