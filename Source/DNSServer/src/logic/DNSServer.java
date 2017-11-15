package logic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class DNSServer {
	public static final int DNSPORT = 53;
	public static final int BUFFERSIZE = 512;
	private DatagramSocket mainSocket;
	private ManejadorBaseDeDatos ips;
	
	public DNSServer() {
		try {
			System.out.println("Loading database...");
			this.ips = new ManejadorBaseDeDatos();
			System.out.println("Initializing server in port " + DNSServer.DNSPORT + "...");
			this.mainSocket = new DatagramSocket(DNSServer.DNSPORT);
		}
		catch (SocketException e) {
			System.err.println("Error: Creando el mainSocket [" + e.getMessage() + "]");
			System.exit(1);
		}
		catch (UnknownHostException e) {
			System.out.println("Error: Ip mal formada [" + e.getMessage() + "]");
			System.exit(1);
		}
		catch (IOException event) {
			System.out.println("Error: [" + event.getMessage() + "]");
			System.exit(1);
		}
	}
	
	public void runServer() {
		System.out.println("Server running...");
		try {
			while (true) {
				byte[] receiveData = new byte[DNSServer.BUFFERSIZE];
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				this.mainSocket.receive(receivePacket);
				
				System.out.println("\n---------------------------------------------------");
				DatagramPacket sendPacket = this.getResponsePacket(receivePacket);
				System.out.println("---------------------------------------------------");
				
				this.mainSocket.send(sendPacket);
			}
		}
		catch (Exception e) {
			this.mainSocket.close();
			System.err.println("Error: Corriendo el servidor");
			System.exit(1);
		}
	}
	
	private DatagramPacket getResponsePacket(DatagramPacket clientPacket) {
		System.out.println("Received a packet with " + clientPacket.getLength() + " bytes");
		QuestionPacket questionPacket = new QuestionPacket(clientPacket.getData());
		
		String hostname = questionPacket.getData();
		System.out.println("Client asking for hostName: " + hostname);
		
		InetAddress foundedAddress = this.getAddressForHostname(hostname);
		if (foundedAddress != null) {
			ResponsePacket responsePacket = new ResponsePacket(questionPacket.getTransactionId(), hostname, questionPacket.getType(), questionPacket.getTypeClass(), foundedAddress);
			return new DatagramPacket(responsePacket.getBytes(), responsePacket.getLength(), clientPacket.getAddress(), clientPacket.getPort());
		}
		return new DatagramPacket(new byte[0], 0, clientPacket.getAddress(), clientPacket.getPort());
	}
	
	private InetAddress getAddressForHostname(String hostname) {
		String sentence = "";
		InetAddress findIP = null;
		if (this.ips.containsHostName(hostname)) { // Se busca en la base de datos
			findIP = this.ips.getHostIp(hostname);
			sentence = hostname + " : " + findIP.getHostAddress();
		} else {
			try { // Sino, se intenta buscar por el DNS alternativo de la maquina
				findIP = InetAddress.getByName(hostname);
				sentence = hostname + " : " + findIP.getHostAddress();
			}
			catch (UnknownHostException event) { // No se encontro
				sentence = "Hostname [" + hostname + "] not found";
			}
		}
		System.out.println(sentence);
		return findIP;
	}
	
	public static void main(String[] args) {
		DNSServer server = new DNSServer();
		server.runServer();
	}
}
