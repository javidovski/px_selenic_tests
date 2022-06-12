package com.UITests.UtilityClasses;


//import com.mongodb.ConnectionString;
//import com.mongodb.client.MongoClient;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClients;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Updates.*;
import static org.junit.jupiter.api.Assertions.fail;

import com.mongodb.client.model.UpdateOptions;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import org.bson.Document;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import com.UITests.TestCase;

public class DatabaseUtils {
	
	private static Connection dbConnection;
	
	
	private static void getConnection() {
		String connectionUrl = "jdbc:sqlserver://wal1at-db1.paytronix.com:1433;"
				+ "database=wal1at_app" + TestCase.APPSERVER + "db;user=sa;password=g1ftc2rd;";
		try {
			dbConnection = DriverManager.getConnection(connectionUrl);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void closeConnection() {
		
		try {
			dbConnection.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void loadDriver() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		}
	}

	public static String getPrintedCardNumber() throws SQLException {
		getConnection();
		loadDriver();
		String printedCardNumber = null;
		ResultSet resultSet = null;
		Statement sql = null;


		//int randomIndex = ThreadLocalRandom.current().nextInt(1,10);
		//System.out.println("The index is " + randomIndex);

		

		try  {


			sql = dbConnection.createStatement();

			String cardListQuery = "SELECT top 1 printed_card_number from card where " + 
					"template_id=" + TestCase.combinedCardTemplateID + " and registration_code=" + TestCase.registrationCode +
					" and status_enum_id=" + TestCase.inactiveStatusEnumID;
			resultSet = sql.executeQuery(cardListQuery);
			//for (int i=0; i< randomIndex; i++) {
			//	resultSet.next();
			//}
			resultSet.next();

			printedCardNumber = resultSet.getString("printed_card_number");
			System.out.println("Using card number " + printedCardNumber);

			/****Lines below would be required if the card selected wasn't inactive****/
			//String cmd = "EXEC px_reset_card 10101010, '" + printedCardNumber + "'";			
			//boolean tf = sql.execute(cmd);


			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			sql.close();
			closeConnection();
		}


		return printedCardNumber;

	}

	public static ArrayList<String> getPrintedCardNumbers(int numCards) throws SQLException {
		getConnection();
		loadDriver();
		ArrayList<String> printedCardNumbers = new ArrayList<String>();
		ResultSet resultSet = null;
		Statement sql = null;


		//int randomIndex = ThreadLocalRandom.current().nextInt(1,10);
		//System.out.println("The index is " + randomIndex);

		

		try  {


			sql = dbConnection.createStatement();

			String cardListQuery = "SELECT top " + numCards + " printed_card_number from card where " + 
					"template_id=" + TestCase.combinedCardTemplateID + " and registration_code=" + TestCase.registrationCode +
					" and status_enum_id=" + TestCase.inactiveStatusEnumID;
			resultSet = sql.executeQuery(cardListQuery);
			for (int i=0; i< numCards; i++) {

				resultSet.next();
				String printedCardNumber = resultSet.getString("printed_card_number");
				System.out.println("The printed_card_number is: " + printedCardNumber);
				printedCardNumbers.add(printedCardNumber);

				/****Lines below would be required if the card selected wasn't inactive****/
				//String cmd = "EXEC px_reset_card 10101010, '" + printedCardNumber + "'";			
				//boolean tf = sql.execute(cmd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			sql.close();
			closeConnection();
		}


		return printedCardNumbers;
	}
	
	/*Get single column value for a single row*/
	public static String getColumnValue(String queryStr, String columnName) throws SQLException {
		getConnection();
		loadDriver();
		
				
		ResultSet resultSet = null;
		Statement sql = null;
		String columnValue = null;
		


		try  {

			sql = dbConnection.createStatement();
			resultSet = sql.executeQuery(queryStr);
			resultSet.next();
			columnValue = resultSet.getString(columnName);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			sql.close();
			closeConnection();
		}


		return columnValue;
	}

	/*Get one or more column values for a single row*/
	public static HashMap<String,String> getColumnValues(String queryStr, String[] columnNames) throws SQLException {
		getConnection();
		loadDriver();
		
				
		ResultSet resultSet = null;
		Statement sql = null;
		HashMap<String,String> columnValues = new HashMap<String,String>();
		
		try  {

			sql = dbConnection.createStatement();
			resultSet = sql.executeQuery(queryStr);
			resultSet.next();
			for (String columnName : columnNames) {
				columnValues.put(columnName,resultSet.getString(columnName));
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			sql.close();
			closeConnection();
		}


		return columnValues;
	}

	
	public static int updateTable(String updateStr) throws SQLException {
		getConnection();
		loadDriver();
		Statement sql = null;
		int rowCount = -1;


		try  {						
			sql = dbConnection.createStatement();
			rowCount = sql.executeUpdate(updateStr); //may return 0
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
			closeConnection();
		}


		return rowCount;
	}
	
	public static int deleteAccountFilter(String label, String merchantID) throws SQLException {
		getConnection();
		loadDriver();
		Statement sql = null;
		int rowCount = -1;
		String deleteAccountFilter = "DELETE FROM account_filter WHERE merchant_id = " + 
				merchantID + " AND label = '" + label + "'";


		try  {						
			sql = dbConnection.createStatement();
			rowCount = sql.executeUpdate(deleteAccountFilter); //may return 0
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
			closeConnection();
		}
		
		return rowCount;	
		
	}
	
	public static void markEmailAsVerified(String printedCardNumber, String merchantID) throws SQLException {
		getConnection();
		loadDriver();
		Statement sql = null;
		ResultSet resultSet = null;
		String cmd = "SELECT a.pxuser_id FROM account a JOIN card c ON c.account_id = a.account_id WHERE c.printed_card_number = '" + printedCardNumber + "' AND c.merchant_id = " + merchantID;
		
		try {
			sql = dbConnection.createStatement();
			resultSet = sql.executeQuery(cmd);
			resultSet.next();
			String pxUserID = resultSet.getString("pxuser_id");
			String cmd2 = "UPDATE pxuser SET last_email_verified_datetime = GETDATE() WHERE merchant_id = " + merchantID + " AND pxuser_id = " + pxUserID;
			sql.execute(cmd2);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
			closeConnection();
		}
		
	}
	
	public static boolean resetCard(String printedCardNumber, String merchantID) throws SQLException {
		getConnection();
		loadDriver();
		Statement sql = null;
		boolean tf = false;		
		String cmd = "EXEC px_reset_card " + merchantID + ", '" + printedCardNumber + "'";

		try  {						
			sql = dbConnection.createStatement();
			tf = sql.execute(cmd);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
			closeConnection();
		}

		return tf;
	}
	
	public static void resetCardsWithEmail(String cardTemplateName, String email, String merchantID) throws SQLException {
		getConnection();
		loadDriver();
		Statement sql = null;
		ResultSet resultSet = null;
		String templateCode = "-1"; 
		
		/*Valid cardTemplateName options are:
		 *  
		 * Combined Card Template
		 * Loyalty Only Card Template
		 * Open SV Card Template
		 * Fixed SV Card Template
		 * Hidden Zero Bal Card Template
		 * Auto Combine Enabled Template
		 * eClub Template
		 * One To One Loyalty Card
		 * Blackhawk Gift Card
		 * Comp Card
		 * 
		 */
		
		try  {

			String cardTemplateCodeQuery = "SELECT card_template_code from card_template WHERE " + 
					"merchant_id=" + merchantID + " AND label='" + cardTemplateName + "'";
			sql = dbConnection.createStatement();
			resultSet = sql.executeQuery(cardTemplateCodeQuery);
			resultSet.next();
			templateCode = resultSet.getString("card_template_code");
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			sql.close();

		}
			
		
		
		ArrayList<String> cardList = new ArrayList<String>();
		String cardListQuery = "SELECT card.printed_card_number FROM pxuser JOIN " +
				"account ON account.pxuser_id = pxuser.pxuser_id AND account.merchant_id = pxuser.merchant_id " + 
				"JOIN card ON card.account_id = account.account_id AND card.merchant_id = account.merchant_id " + 
				"JOIN card_template ON card.template_id = card_template.template_id " + 
				"WHERE card.merchant_id = " + merchantID + " " + 
				"AND pxuser.email = '" + email + "' " + 
				"AND card_template.card_template_code = " + templateCode;
		//System.out.println(cardListQuery);
		try  {


			sql = dbConnection.createStatement();
			resultSet = sql.executeQuery(cardListQuery);
			while(resultSet.next()) {
				System.out.println(resultSet.getString("printed_card_number"));
				cardList.add(resultSet.getString("printed_card_number"));
			}
		}


		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			sql.close();

		}

		for (String card : cardList) {
			
			try  {
				//undo combine
				//System.out.println("Undo combine");
				String getIDs = "SELECT top 1 combined_card_id, abandoned_account_id FROM account_combine " + 
						"JOIN card ON card.card_id = account_combine.combined_card_id " + 
						"AND card.card_number = " + card + " " + 
						"AND account_combine.merchant_id = " + merchantID;
				//System.out.println(getIDs);
				sql = dbConnection.createStatement();				
				resultSet = sql.executeQuery(getIDs);
				if (resultSet.next()) {
					String combinedCardID = resultSet.getString("combined_card_id");
					//System.out.println(combinedCardID);
					String abandonedAccountID = resultSet.getString("abandoned_account_id");
					//System.out.println(abandonedAccountID);

					String updateAccountIDQuery = "UPDATE card SET account_id = " + abandonedAccountID + " " + 
							"WHERE merchant_id = " + merchantID + " AND card_id = " + combinedCardID;
					//System.out.println(updateAccountIDQuery);
					sql.execute(updateAccountIDQuery);

					String updateStatusQuery = "UPDATE account SET status_enum_id = " + 
							"dbo.pxi_enum_id_from_category_value('Account Status', 'ACTIVE') " + 
							"WHERE merchant_id = " + merchantID + "AND account_id = " + abandonedAccountID;
					//System.out.println(updateStatusQuery);
					sql.execute(updateStatusQuery);

					sql.execute("EXEC dbo.px_update_primary_card_id " + merchantID + ", " + abandonedAccountID);

					String deleteRecordForAccountCombinationQuery = "DELETE FROM account_combine " + 
							"WHERE merchant_id = " + merchantID + "AND combined_card_id = " + combinedCardID;
					//System.out.println(deleteRecordForAccountCombinationQuery);
					sql.execute(deleteRecordForAccountCombinationQuery);
				}

				//undo attach
				//System.out.println("Undo attach");
				String lookupAccountResultQuery  = "SELECT top 1 attached_account_id, abandoned_pxuser_id " + 
						"FROM account_attach " + 
						"JOIN card ON card.account_id = account_attach.attached_account_id " + 
						"AND card.card_number = " + card + " " +
						"AND account_attach.merchant_id = " + merchantID;
				//System.out.println(lookupAccountResultQuery);
				resultSet = sql.executeQuery(lookupAccountResultQuery);
				if (resultSet.next()) {
					String attachedAccountID = resultSet.getString("attached_account_id");
					//System.out.println(attachedAccountID);
					String abandonedPXUserID = resultSet.getString("abandoned_pxuser_id");
					//System.out.println(abandonedPXUserID);
					
					String updateAccountIdResultQuery = "UPDATE account  SET pxuser_id = " + abandonedPXUserID + 
							" , primary_card_id = " + abandonedPXUserID + " " + 
							"WHERE merchant_id = " + merchantID + " " + 
							"AND account_id = " + attachedAccountID;
					//System.out.println(updateAccountIdResultQuery);
					sql.execute(updateAccountIdResultQuery);
					
					String updateTypeResultQuery  = "UPDATE pxuser SET type_enum_id = dbo.pxi_enum_id_from_category_value('Pxuser Type', 'Cardholder') " + 
					"WHERE merchant_id = " + merchantID + " AND pxuser_id = " + abandonedPXUserID;
					//System.out.println(updateTypeResultQuery);
					sql.execute(updateTypeResultQuery);
					
					String deleteFromAccountAttachResult = "DELETE FROM account_attach " + 
					"WHERE merchant_id = " + merchantID + " " + 
					"AND attached_account_id = " + attachedAccountID;
					//System.out.println(deleteFromAccountAttachResult);
					sql.execute(deleteFromAccountAttachResult);

				}
				
				//reset card
				sql.execute("EXEC px_reset_card " + merchantID + ", " + card);
			}
			catch (SQLException e) {
				e.printStackTrace();
			} finally {
				resultSet.close();
				sql.close();
				

			}
		}

		closeConnection();
	}

	public static int deleteSurvey(String surveyCode, String merchantID) throws SQLException {
		getConnection();
		loadDriver();
		Statement sql = null;
		int rowCount = -1;
		
		String queryStr = "DELETE FROM survey_answer WHERE survey_participant_id IN (SELECT survey_participant_id FROM survey_participant WHERE survey_id = (SELECT survey_id FROM survey WHERE merchant_id = " + merchantID + " AND code = '" + surveyCode + "'))\r\n" + 
				"DELETE FROM survey_participant WHERE survey_id = (SELECT survey_id FROM survey WHERE merchant_id = " + merchantID + " AND code = '" + surveyCode + "')\r\n" + 
				"DELETE FROM survey_question_item WHERE survey_question_id IN (SELECT survey_question_id FROM dbo.survey_question WHERE survey_id = (SELECT survey_id FROM survey WHERE merchant_id = " + merchantID + " AND code = '" + surveyCode + "'))\r\n" + 
				"DELETE FROM survey_question WHERE survey_id = (SELECT survey_id FROM survey WHERE merchant_id = " + merchantID + " AND code = '" + surveyCode + "')\r\n" +
				"DELETE FROM survey WHERE merchant_id = " + merchantID + " AND code = '" + surveyCode + "'";
		
		try  {						
			sql = dbConnection.createStatement();
			rowCount = sql.executeUpdate(queryStr); //may return 0
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
			closeConnection();
		}
		
		return rowCount;
		
	}
	
	public static int deletePXRule(String rule_name, String merchantID) throws SQLException {
		//Deletes a pxrule, unattaching it from a card template in the process

		getConnection();
		loadDriver();
		Statement sql = null;
		int rowCount = -1;

		String queryStr = "DELETE FROM template_rule WHERE pxrule_id = (SELECT pxrule_id FROM pxrule WHERE rule_name = '" + rule_name + "' AND merchant_id = " + merchantID + ")\r\n" +
				"DELETE FROM pxrule WHERE rule_name = '" + rule_name + "' AND merchant_id = " + merchantID;

		try  {						
			sql = dbConnection.createStatement();
			rowCount = sql.executeUpdate(queryStr); //may return 0

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
			closeConnection();
		}

		return rowCount;
	}
	
	public static int deleteCampaignMessageTemplate(String label, String merchantID) throws SQLException {
		//Deletes a pxrule, unattaching it from a card template in the process

		getConnection();
		loadDriver();
		Statement sql = null;
		int rowCount = -1;

		  String queryStr = "DELETE FROM activity_notification_delivery WHERE merchant_id = " + merchantID + 
				  " AND activity_notification_id IN (SELECT activity_notification_id FROM activity_notification WHERE merchant_id = " + merchantID + 
				  " AND activity_notification_template_id = (SELECT activity_notification_template_id FROM activity_notification_template WHERE merchant_id = " + merchantID + " AND label = '" + label + "'))\r\n" + 
               	  "DELETE FROM activity_notification WHERE merchant_id = " + merchantID + 
               	  " AND activity_notification_template_id = (SELECT activity_notification_template_id FROM activity_notification_template WHERE merchant_id = " + merchantID + " AND label = '" + label + "')\r\n" +
                  "DELETE FROM activity_notification_template WHERE merchant_id = " + merchantID + " AND label = '" + label + "'";

		try  {						
			sql = dbConnection.createStatement();
			rowCount = sql.executeUpdate(queryStr); //may return 0

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.close();
			closeConnection();
		}

		return rowCount;
	}
	
	public static String getConfigNodeJSON(String aspect, String context) {
		//For versions higher than 3.5
		//ConnectionString connString = new ConnectionString("mongodb://wal1-mongodb1.paytronix.com/?w=majority");
		//MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connString).retryWrites(true).build();
		//MongoClient mongoClient = MongoClients.create(settings);

		
		Block<Document> printBlock = new Block<Document>() {
		       @Override
		       public void apply(final Document document) {
		           System.out.println(document.toJson());
		       }
		};
		
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://wal1-mongodb1.paytronix.com/?w=majority"));
		MongoDatabase database = mongoClient.getDatabase(TestCase.SERVER);
		System.out.println(database.getName());

		MongoCollection<Document> configNodes = database.getCollection("configuration.nodes");
		System.out.println(configNodes.count());
		//configNodes.find(and(eq("aspect", "activitynotification.SiteConfig"),eq("context","site corp"))).forEach(printBlock);
		ArrayList<String> myJSON = configNodes.find(and(eq("aspect",aspect),
				eq("context",context))).map(Document::toJson).into(new ArrayList<>());
		return myJSON.get(0);
		
	}
	
	public static void changeToSpreedly() {
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://wal1-mongodb1.paytronix.com/?w=majority"));
		MongoDatabase database = mongoClient.getDatabase(TestCase.SERVER);
		MongoCollection<Document> configNodes = database.getCollection("configuration.nodes");
		
		//https://mongodb.github.io/mongo-java-driver/3.6/driver/tutorials/perform-write-operations/
		/*configNodes.updateOne(
                and(eq("aspect", aspect),eq("context",context)),
                combine(set(nameToUpdate,newValue ), 
                		set("contents.serviceWaitTime.$numberLong", "3500"))); */
		
		String aspect = "spreedly.config";
		String context = "global";
		String environmentKey = "UVBQUACRGgYvMzvAaEneXds+X1SKGS4tZWdNxaf7+mpYjHVPipaaPkvH5eBRU0jdyyg0Nzzuv4faL6xg";
		String accessSecret = "z87OzgD9l5caaEMqPqPfbZ8fRTgeyxpYEHXRzRQr0WAyNfBGSkJbGOFbsUppPl+yudFhjiQOxbVlQmTmh5jD+3AEgSd/SRvxD/NNvJpHcId7EjlRvVT6a2Kg0VreFBNd/g==";
		configNodes.updateOne(and(eq("aspect", aspect),eq("context",context)), 
				set("contents",new Document().append("environmentKey",
						environmentKey).append("accessSecret",accessSecret)));
		
		
		//Usage:
		/*
	    System.out.println("Value of spreedly.config before modification: " + DatabaseUtils.getConfigNodeJSON("spreedly.config",
				"global"));
		DatabaseUtils.changeToSpreedly();
		System.out.println("Value of spreedly.config after modification: " + DatabaseUtils.getConfigNodeJSON("spreedly.config",
				"global"));
		*/
		
		
	}
	

	public static void changeCampaignBuilderVersion(String versionName) {
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://wal1-mongodb1.paytronix.com/?w=majority"));
		MongoDatabase database = mongoClient.getDatabase(TestCase.SERVER);
		MongoCollection<Document> configNodes = database.getCollection("configuration.nodes");
				
		String aspect = "campaignbuilder.GlobalConfig";
		String context = "global";
		configNodes.updateOne(and(eq("aspect", aspect),eq("context",context)), 
				set("contents.campaignBuilderVersionConfig.defaultEditor", versionName));
	}

}


