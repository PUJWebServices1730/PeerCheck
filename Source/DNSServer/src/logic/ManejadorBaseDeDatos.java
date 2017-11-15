package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ManejadorBaseDeDatos {
	public static final String DATABASE = "./MasterFile.txt";
	private Map<String, InetAddress> ips;
	
	public ManejadorBaseDeDatos() throws IOException {
		this.ips = new HashMap<>();
		this.loadDataBase();
	}
	
	private void loadDataBase() throws IOException {
		BufferedReader reader = Tools.getNewBufferedReader(ManejadorBaseDeDatos.DATABASE);
		String linea = null;
		String token = null;
		String hostName = null;
		String hostip = null;
		String type = null;
		boolean isReading = false;
		while ((linea = Tools.readLine(reader)) != null) {
			linea = linea.trim();
			StringTokenizer tokenLine = new StringTokenizer(linea, " ");
			tokenLine.countTokens();
			if (linea.startsWith("$ORIGIN")) {
				isReading = false;
				tokenLine.nextToken();
				token = tokenLine.nextToken();
				System.out.println();
			} else if (linea.startsWith("; hosts")) {
				isReading = true;
			} else if (isReading && (tokenLine.countTokens() == 4)) {
				hostName = tokenLine.nextToken().trim() + "." + token;
				tokenLine.nextToken().trim();
				type = tokenLine.nextToken().trim();
				hostip = tokenLine.nextToken().trim();
				
				if (type.equals("A")) {
					InetAddress ip = InetAddress.getByName(hostip);
					this.addHost(hostName, ip);
					System.out.printf("%-30s : %-15s\n", hostName, hostip);
				}
			} else {
				isReading = false;
			}
		}
		System.out.println();
		Tools.closeBufferedReader(reader);
	}
	
	public void addHost(String hostName, InetAddress ip) {
		this.ips.put(hostName, ip);
	}
	
	public InetAddress getHostIp(String hostname) {
		return this.ips.get(hostname);
	}
	
	public boolean containsHostName(String hostname) {
		return this.ips.containsKey(hostname);
	}
}
