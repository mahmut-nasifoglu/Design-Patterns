package com.mhmtnasif;

import java.util.HashMap;
import java.util.Map;

class Database {
	private char[] tab = new char[100];
	private static Database instanceDatabase = null;

	private Database() {
	}

	private static Database instanceOfDatabase() {
		if (instanceDatabase == null) {
			synchronized (Database.class) {
				if (instanceDatabase == null) {
					instanceDatabase = new Database();
				}
			}
		}
		return instanceDatabase;
	}

	public static IConnection getConnection() {
		return Connection.getInstance();
	}

	private static class Connection implements IConnection {
		private Database db = instanceOfDatabase();
		private static Map<Integer, Connection> a = new HashMap<Integer, Connection>();
		private static Integer index = 0;

		public static IConnection getInstance() {
			index++;
			Connection instanceConnection = a.get(index);
			if (instanceConnection == null) {
				synchronized (Connection.class) {
					if (instanceConnection == null) {
						instanceConnection = new Connection();
						a.put(index, instanceConnection);
					}
				}
			}
			if (index == 3) {
				index = 0;
			}
			return instanceConnection;
		}


		public char get(int index) {
			return db.tab[index];
		}

		public void set(int index, char c) {
			db.tab[index] = c;
		}

		public int length() {
			return db.tab.length;
		}
	}
}

