package gui.listeners.action;

import gui.frames.InvoiceWindow;
import gui.frames.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAndShowInvoiceAction implements ActionListener {
	private final MainWindow outer;

	public CreateAndShowInvoiceAction(MainWindow outer) {
		this.outer = outer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String invoiceDescription = outer.controller.getInvoiceDescription();
		@SuppressWarnings("unused")
		InvoiceWindow invoiceWindow = new InvoiceWindow(invoiceDescription);
	}

}
