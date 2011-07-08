/*
GanttProject is an opensource project management tool. License: GPL2
Copyright (C) 2005-2011 Dmitry Barashev

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/
package net.sourceforge.ganttproject.action;

import java.awt.event.ActionEvent;

import javax.swing.event.UndoableEditEvent;

import net.sourceforge.ganttproject.gui.UIFacade;
import net.sourceforge.ganttproject.language.GanttLanguage;
import net.sourceforge.ganttproject.undo.GPUndoListener;
import net.sourceforge.ganttproject.undo.GPUndoManager;

/**
 * @author bard
 */
public class RedoAction extends GPAction implements GPUndoListener {
    private GPUndoManager myUndoManager;
    private UIFacade myUiFacade;

    public RedoAction(GPUndoManager undoManager, UIFacade uiFacade) {
        super("redo");
        myUndoManager = undoManager;
        myUndoManager.addUndoableEditListener(this);
        myUiFacade = uiFacade;
        setEnabled(myUndoManager.canRedo());
    }

    public void actionPerformed(ActionEvent e) {
        myUiFacade.setStatusText(GanttLanguage.getInstance().getText("redo"));
        myUndoManager.redo();
    }

    public void undoableEditHappened(UndoableEditEvent e) {
        setEnabled(myUndoManager.canRedo());
    }

    public void undoOrRedoHappened() {
        setEnabled(myUndoManager.canRedo());
    }

    protected String getIconFilePrefix() {
        return "redo_";
    }
}
