package crossing.e1.taskintegrator.widgets;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class GroupXSLTagAttribute extends Group {
	private Text txtAttributeName;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public GroupXSLTagAttribute(Composite parent, int style, boolean showRemoveButton) {
		super(parent, style);
		setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Combo cmbAttributeType = new Combo(this, SWT.NONE);
		cmbAttributeType.setLayoutData(new RowData(148, SWT.DEFAULT));
		
		txtAttributeName = new Text(this, SWT.BORDER);
		txtAttributeName.setLayoutData(new RowData(167, SWT.DEFAULT));
		
		if(showRemoveButton){
			Button btnRemove = new Button(this, SWT.NONE);
			btnRemove.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			btnRemove.setText("Remove");
		}
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
