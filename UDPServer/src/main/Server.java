package main;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;

public class Server {
	
	public static void main(String[] args) throws Exception {
		
		DatagramSocket serverSocket = new DatagramSocket(12000);
		
		byte[] receiveData = new byte[1024];
		
		while(true) {
			DatagramPacket packetFromClient = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(packetFromClient);
			
			InetAddress address = packetFromClient.getAddress();
			int portNumber = packetFromClient.getPort();			
			String input = new String(packetFromClient.getData()).trim(); //trims extra white space from buffer
						
			String result = "";			
			for (int i = input.length()-1; i>=0; i--) {
				result += input.charAt(i);
			}
			
			byte[] sendData = result.getBytes();
			DatagramPacket packetForClient = new DatagramPacket(sendData, sendData.length, address, portNumber);
			serverSocket.send(packetForClient);
			
		}		
	}
}
