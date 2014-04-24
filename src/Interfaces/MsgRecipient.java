package Interfaces;

public interface MsgRecipient<E> {
	public void sendSMSwithList(E[] list, int phoneNumber);
	public void sendEmail(E[] list, String Email);
	
}
