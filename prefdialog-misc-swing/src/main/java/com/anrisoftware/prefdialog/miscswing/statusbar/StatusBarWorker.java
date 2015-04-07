/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.statusbar;

import static com.anrisoftware.prefdialog.miscswing.statusbar.EndItem.endItem;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.inject.Inject;
import javax.swing.SwingWorker;

/**
 * Updates the status bar message.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class StatusBarWorker extends SwingWorker<Void, StatusBarItem> {

    private final BlockingQueue<StatusBarItem> queue;

    @Inject
    private StatusBarWorkerLogger log;

    private long timeout;

    private StatusBarItem oldItem;

    StatusBarWorker() {
        this.queue = new LinkedBlockingQueue<StatusBarItem>();
        this.timeout = 500;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void add(StatusBarItem item) {
        if (oldItem == null) {
            log.addItem(item);
            queue.offer(item);
            return;
        }
        if (oldItem.equals(item)
                && (item.getTime() - oldItem.getTime() > timeout)) {
            log.addItem(oldItem, item);
            queue.offer(item);
            return;
        }
        if (!oldItem.equals(item)) {
            log.addItem(oldItem, item);
            queue.offer(item);
            return;
        }
    }

    public void stop() {
        queue.offer(endItem);
    }

    @Override
    protected Void doInBackground() throws Exception {
        StatusBarItem item;
        while ((item = queue.poll(timeout, MILLISECONDS)) != endItem) {
            oldItem = item;
            if (item != null) {
                publish(item);
            }
        }
        log.timerEnd(item);
        return null;
    }

    @Override
    protected void process(List<StatusBarItem> items) {
        for (StatusBarItem item : items) {
            log.itemSet(item);
            item.run();
        }
    }

}
