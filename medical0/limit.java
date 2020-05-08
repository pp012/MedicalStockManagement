package medical0;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class limit extends PlainDocument{
private int l;
public limit(int limitation)
{
	this.l=limitation;
}
public void insertString(int offset,String str,AttributeSet set)  throws BadLocationException
{
	if(str==null)
	{
		return;
	}
	else if(getLength() +str.length()<= l)
	{
		str=str.toUpperCase();
		super.insertString(offset, str, set);
	}
}
}
