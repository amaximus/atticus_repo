package com.atticus.CallMap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import android.text.format.Time;
// import android.util.Log;
import android.util.SparseArray;

public class Country_Code {
	
	//Map<Integer, String> prefix2country = new HashMap<Integer, String>();
	SparseArray<String> prefix2iso = new SparseArray<String>();

	Map<String, String> ISOmcc2country = new HashMap<String, String>();
	Map<String, String> ISOmcc2countryN = new HashMap<String, String>();
	Map<String, String> ISOmcc2STD = new HashMap<String, String>();
	Map<String, Integer> ISOmcc2DSToffset = new HashMap<String, Integer>();

	public Country_Code() {
		
		// country code prefix => ISO3166-1
		String p2i[] = {"20","EG","211","SS","212","MA","213","DZ","216","TN","218","LY",
				"220","GM","221","SN","222","MR","223","ML","224","GN","225","CI","226","BF","227","NE","228","TG","229","BJ",
				"230","MU","231","LR","232","SL","233","GH","234","NG","235","TD","236","CF","237","CM","238","CV","239","ST",
				"240","GQ","241","GA","242","CG","243","CD","244","AO","245","GW","246","IO","247","AC","248","SC","249","SD",
				"250","RW","251","ET","252","SO","253","DJ","254","KE","255","TZ","256","UG","257","BI","258","MZ",
				"260","ZM","261","MG","262","RE","263","ZW","264","NA","265","MW","266","LS","267","BW","268","SZ","269","KM",
				"27","ZA","290","SH","291","ER","297","AW","298","FO","290","GL",
				"30","GR","31","NL","32","BE","33","FR","34","ES",
				"350","GI","351","PT","352","LU","353","IE","354","IS","355","AL","356","MT","357","CY","358","FI","359","BG",
				"36","HU","370","LT","371","LV","372","EE","373","MD","374","AM","375","BY","376","AD","377","MC","378","SM","379","VA",
				"380","UA","381","RS","382","ME","385","HR","386","SI","387","BA","389","MK","39","IT",
				"40","RO","41","CH","420","CZ","421","SK","423","LI","43","AT","44","UK","45","DK","46","SE","47","NO","48","PL","49","DE",
				"500","FK","501","BZ","502","GT","503","SV","504","HN","505","NI","506","CR","507","PA","508","PM","509","HT",
				"51","PE","52","MX","53","CU","54","AR","55","BR","56","CL","57","CO","58","VE",
				"590","GP","591","BO","592","GY","593","EC","594","GF","595","PY","596","MQ","597","SR","598","UY","599","CW",
				"60","MY","61","AU","62","ID","63","PH","64","NZ","65","SG","66","TH",
				"670","TL","672","NF","673","BN","674","NR","675","PG","676","TO","677","SB","678","VU","679","FJ",
				"680","PW","681","WF","682","CK","683","NU","685","WS","686","KI","687","NC","688","TV","689","PF",
				"690","TK","691","FM","692","MH",
				"7","RU",
				"81","JP","82","KR","84","VN","850","KP","852","HK","853","MO","855","KH","856","LA","86","CN",
				"880","BD","886","TW",
				"90","TR","91","IN","92","PK","93","AF","94","LK","95","MM",
				"960","MV","961","LB","962","JO","963","SY","964","IQ","965","KW","966","SA","967","YE","968","OM",
				"970","PS","971","AE","972","IL","973","BH","974","QA","975","BT","976","MN","977","NP","98","IR",
				"992","TJ","993","TM","994","AZ","995","GE","996","KG","998","UZ",
				"1242","BS","1246","BB","1264","AI","1268","AG","1284","VG","1340","VI","1345","KY","1441","BM","1473","GD",
				"1649","TC","1664","MS","1670","MP","1671","GU","1684","AS",
				"1721","SX","1758","LC","1767","DM","1784","VC","1787","PR",
				"1809","DO","1868","TT","1869","KN","1876","JM","1939","PR",
				// U S A
				"1205","USAL","1251","USAL","1256","USAL","1334","USAL","1659","USAL","1938","USAL", // Alabama
				"1907","USAK","1250","USAK",	//Alaska
				"1480","USAZ","1520","USAZ","1602","USAZ","1623","USAZ","1928","USAZ",	// Arizona
				"1327","USAR","1479","USAR","1501","USAR","1870","USAR",	// Arkansas
				"1209","USCA","1213","USCA","1310","USCA","1323","USCA","1341","USCA","1369","USCA","1408","USCA",
				"1415","USCA","1424","USCA","1442","USCA","1510","USCA","1530","USCA","1559","USCA","1562","USCA",
				"1619","USCA","1626","USCA","1627","USCA","1628","USCA","1650","USCA","1657","USCA","1661","USCA",
				"1669","USCA","1707","USCA","1714","USCA","1747","USCA","1760","USCA","1764","USCA","1805","USCA",
				"1818","USCA","1831","USCA","1858","USCA","1909","USCA","1916","USCA","1925","USCA","1935","USCA",
				"1949","USCA","1951","USCA",	// California
				"1303","USCO","1719","USCO","1720","USCO","1970","USCO",	// Colorado
				"1203","USCT","1475","USCT","1860","USCT","1959","USCT",	// Connecticut
				"1302","USDE",	// Delaware
				"1202","USDC",	// Washington DC
				"1239","USFL","1305","USFL","1321","USFL","1352","USFL","1386","USFL","1407","USFL","1561","USFL",
				"1689","USFL","1727","USFL","1754","USFL","1772","USFL","1786","USFL","1813","USFL","1850","USFL",
				"1863","USFL","1904","USFL","1941","USFL","1954","USFL",	// Florida
				"1229","USGA","1404","USGA","1470","USGA","1478","USGA","1678","USGA","1706","USGA","1762","USGA",
				"1770","USGA","1912","USGA",	// Georgia
				"1808","USHI",	// Hawaii
				"1208","USID",	// Idaho
				"1217","USIL","1224","USIL","1309","USIL","1312","USIL","1331","USIL","1447","USIL","1464","USIL",
				"1618","USIL","1630","USIL","1708","USIL","1730","USIL","1773","USIL","1779","USIL","1815","USIL",
				"1847","USIL","1872","USIL",	// Illinois
				"1219","USIN","1260","USIN","1317","USIN","1574","USIN","1765","USIN","1812","USIN",	// Indiana
				"1319","USIA","1515","USIA","1563","USIA","1641","USIA","1712","USIA",	// Iowa
				"1316","USKS","1620","USKS","1785","USKS","1913","USKS",	// Kansas
				"1270","USKY","1364","USKY","1502","USKY","1606","USKY","1859","USKY",	// Kentucky
				"1225","USLA","1318","USLA","1337","USLA","1504","USLA","1985","USLA",	// Louisiana
				"1207","USME",	// Maine
				"1227","USMD","1240","USMD","1301","USMD","1410","USMD","1443","USMD","1667","USMD",	// Maryland
				"1339","USMA","1351","USMA","1413","USMA","1508","USMA","1617","USMA","1774","USMA","1781","USMA",
				"1857","USMA","1978","USMA",	// Massachusetts
				"1231","USMI","1248","USMI","1269","USMI","1313","USMI","1517","USMI","1586","USMI","1616","USMI",
				"1679","USMI","1734","USMI","1810","USMI","1906","USMI","1947","USMI","1989","USMI",	// Michigan
				"1218","USMN","1320","USMN","1507","USMN","1612","USMN","1651","USMN","1763","USMN","1952","USMN",	// Minnesota
				"1228","USMS","1601","USMS","1662","USMS","1769","USMS",	// Mississippi
				"1314","USMO","1417","USMO","1557","USMO","1573","USMO","1636","USMO","1660","USMO","1816","USMO",
				"1975","USMO",	// Missouri
				"1406","USMT",	// Montana
				"1308","USNE","1402","USNE","1531","USNE",	// Nebraska
				"1702","USNV","1402","USNV","1531","USNV",	// Nevada
				"1603","USNH",	// New Hampshire
				"1201","USNJ","1551","USNJ","1609","USNJ","1732","USNJ","1848","USNJ","1856","USNJ","1862","USNJ",
				"1908","USNJ","1973","USNJ",	// New Jersey
				"1505","USNM","1575","USNM",	// New Mexico
				"1212","USNY","1315","USNY","1347","USNY","1516","USNY","1518","USNY","1585","USNY","1607","USNY",
				"1631","USNY","1646","USNY","1716","USNY","1718","USNY","1845","USNY","1914","USNY","1917","USNY",
				"1929","USNY",	// New York
				"1252","USNC","1336","USNC","1704","USNC","1828","USNC","1910","USNC","1919","USNC","1980","USNC",
				"1984","USNC",	// North Carolina
				"1701","USND",	// North Dakota
				"1216","USOH","1234","USOH","1283","USOH","1330","USOH","1380","USOH","1419","USOH","1440","USOH",
				"1513","USOH","1567","USOH","1614","USOH","1740","USOH","1937","USOH",	// Ohio
				"1405","USOK","1539","USOK","1580","USOK","1918","USOK",	// Oklahoma
				"1458","USOR","1503","USOR","1541","USOR","1971","USOR",	// Oregon
				"1215","USPA","1267","USPA","1272","USPA","1412","USPA","1445","USPA","1484","USPA","1570","USPA",
				"1582","USPA","1610","USPA","1717","USPA","1724","USPA","1814","USPA","1835","USPA","1878","USPA",	// Pennsylvania
				"1401","USRI",	// Rhode Island
				"1803","USSC","1843","USSC","1864","USSC",	// South Carolina
				"1605","USSD",	// South Dakota
				"1423","USTN","1615","USTN","1731","USTN","1865","USTN","1901","USTN","1931","USTN",	// Tennessee
				"1210","USTX","1214","USTX","1254","USTX","1281","USTX","1325","USTX","1361","USTX","1409","USTX",
				"1430","USTX","1432","USTX","1469","USTX","1512","USTX","1682","USTX","1713","USTX","1737","USTX",
				"1806","USTX","1817","USTX","1830","USTX","1832","USTX","1903","USTX","1915","USTX","1936","USTX",
				"1940","USTX","1956","USTX","1972","USTX","1979","USTX",	// Texas
				"1385","USUT","1435","USUT","1801","USUT",	// Utah
				"1802","USVT",	// Vermont
				"1276","USVA","1434","USVA","1540","USVA","1571","USVA","1703","USVA","1757","USVA","1804","USVA",	// Virginia
				"1206","USWA","1253","USWA","1360","USWA","1425","USWA","1509","USWA","1564","USWA",	// Washington
				"1304","USWV","1681","USWV",	//West Virginia
				"1262","USWI","1274","USWI","1414","USWI","1534","USWI","1608","USWI","1715","USWI","1920","USWI",	// Wisconsin
				"1307","USWY",	// Wyoming
				// C A N A D A
				"1403","CAAB","1587","CAAB","1780","CAAB","1825","CAAB",	// Alberta
				"1236","CABC","1250","CABC","1604","CABC","1672","CABC","1778","CABC",	// British Columbia
				"1204","CAMB","1431","CAMB",	// Manitoba
				"1506","CANB",	// New Brunswick
				"1709","CANL",	// Newfoundland and Labrador
				"1902","CANS",	// Nova Scotia
				"1226","CAON","1249","CAON","1289","CAON","1343","CAON","1365","CAON","1416","CAON","1437","CAON",
				"1519","CAON","1613","CAON","1647","CAON","1705","CAON","1807","CAON","1905","CAON",	// Ontario
				"1902","CAPE",	// Prince Edward Island
				"1418","CAQC","1438","CAQC","1450","CAQC","1514","CAQC","1579","CAQC","1581","CAQC","1819","CAQC",
				"1873","CAQC",	// Quebec
				"1306","CASK","1639","CASK",	// Saskatchewan
				"1867","CAYT"	// Yukon
				};
		
		// ISO3166-1 => country
		String i2cEn[] = {"AF","Afghanistan","AL","Albania","DZ","Algeria","AS","American Samoa","AD","Andorra","AO","Angola",
				"AI","Anguilla","AG","Antigua and Barbuda","AR","Argentina","AM","Armenia","AW","Aruba","AU","Australia",
				"AT","Austria","AZ","Azerbaijan",
				"BS","Bahamas","BH","Bahrain","BD","Bangladesh","BB","Barbados","BY","Belarus","BE","Belgium","BZ","Belize",
				"BJ","Benin","BM","Bermuda","BT","Bhutan","BO","Bolivia","BA","Bosna i Hercegovina","BW","Botswana","BR","Brazil",
				"VG","British Virgin Islands","BN","Brunei Darussalam","BG","Bulgaria","BF","Burkina Faso","BI","Burundi",
				"KH","Cambodia","CM","Cameroon","CA","Canada","CV","Cape Verde","KY","Cayman Islands","CF","Central African Republic",
				"TD","Chad","CL","Chile","CN","China","CO","Colombia","KM","Comoros","CG","Republique du Congo","CK","Cook Islands",
				"CR","Costa Rica","CI","Côte d'Ivoire","HR","Croatia","CU","Cuba","CW","Curaçao","CY","Cyprus","CZ","Czech Republic","CD","Congo",
				"DK","Denmark","DJ","Djibouti","DM","Dominica","DO","República Dominicana",
				"TL","East Timor","EC","Ecuador","EG","Egypt","SV","El Salvador","GQ","Guinea Ecuatorial","ER","Eritrea","EE","Estonia","ET","Ethiopia",
				"FK","Falkland Islands","FO","Faroe Islands","FJ","Fiji","FI","Finland","FR","France","GF","French Guiana","PF","French Polynesia",
				"GA","Gabon","GM","Gambia","GE","Georgia","DE","Germany","GH","Ghana","GI","Gibraltar","GR","Greece","GL","Greenland",
				"GD","Grenada","GP","Guadeloupe","GU","Guam","GT","Guatemala","GN","Guinea","GW","Guinea-Bissau","GY","Guyana",
				"HT","Haiti","HN","Honduras","HK","Hong Kong","HU","Hungary",
				"IS","Iceland","IN","India","IN","India","ID","Indonesia","IR","Iran","IQ","Iraq","IE","Ireland","IL","Israel","IT","Italy",
				"JM","Jamaica","JP","Japan","JO","Jordan",
				"KZ","Kazakhstan","KE","Kenya","KI","Kiribati","KP","North Korea","KR","South Korea","KW","Kuwait","KG","Kyrgyz Republic",
				"LA","Laos","LV","Latvia","LB","Lebanon","LS","Lesotho","LR","Liberia","LY","Libya","LI","Liechtenstein","LT","Lithuania","LU","Luxembourg",
				"MO","Macau","MK","Macedonia","MG","Madagascar","MW","Malawi","MY","Malaysia","MV","Maldives","ML","Mali","MT","Malta",
				"MH","Marshall Islands","MQ","Martinique","MR","Mauritania","MU","Mauritius","MX","México","FM","Federated States of Micronesia",
				"MD","Moldova","MC","Monaco","MN","Mongolia","ME","Montenegro","MS","Montserrat","MA","Morocco","MZ","Mozambique","MM","Myanmar",
				"NA","Namibia","NR","Nauru","NP","Nepal","NL","Netherlands","NC","New Caledonia","NZ","New Zealand","NI","Nicaragua",
				"NE","Niger","NG","Nigeria","NU","Niue","MP","Northern Mariana Islands","NO","Norway",
				"OM","Oman",
				"PK","Pakistan","PW","Palau","PS","Palestine","PA","Panama","PG","Papua New Guinea","PY","Paraguay","PE","Perú",
				"PH","Philippines","PL","Poland","PT","Portugal","PR","Puerto Rico",
				"QA","Qatar",
				"RE","Réunion","RO","Romania","RU","Russian Federation","RW","Rwanda",
				"KN","Saint Kitts and Nevis","LC","Saint Lucia","PM","Saint Pierre et Miquelon","VC","Saint Vincent and the Grenadines",
				"WS","Samoa","SM","San Marino","ST","São Tomé e Príncipe","SA","Saudi Arabia","SN","Senegal","RS","Serbia",
				"SC","Seychelles","SL","Sierra Leone","SG","Singapore","SK","Slovakia","SI","Slovenia","SB","Solomon Islands",
				"SO","Somalia","ZA","South Africa","ES","Spain","LK","Sri Lanka","SD","Sudan","SR","Suriname","SZ","Swaziland",
				"SE","Sweden","CH","Switzerland","SY","Syria",
				"TW","Taiwan","TJ","Tajikistan","TZ","Tanzania","TH","Thailand","TG","Togo","TO","Tonga","TT","Trinidad and Tobago",
				"TN","Tunisia","TR","Turkey","TM","Turkmenistan","TC","Turks and Caicos Islands",
				"UG","Uganda","UA","Ukraine","AE","United Arab Emirates","GB","United Kingdom","US","United States of America",
				"VI","United States Virgin Islands","UY","Uruguay","UZ","Uzbekistan",
				"VU","Vanuatu","VA","Vatican City State","VE","Venezuela","VN","Viet Nam",
				"WF","Wallis and Futuna",
				"YE","Yemen",
				"ZM","Zambia","ZW","Zimbabwe",
				// U S A
				"USAL","Alabama","USAK","Alaska","USAZ","Arizona","USAR","Arkansas","USCA","California","USCO","Colorado",
				"USCT","Connecticut","USDE","Delaware","USDC","Washington DC.","USFL","Florida","USGA","Georgia",
				"USHI","Hawaii","USID","Idaho","USIL","Illinois","USIN","Indiana","USIA","Iowa","USKS","Kansas",
				"USKY","Kentucky","USLA","Louisiana","USME","Maine","USMD","Maryland","USMA","Massachusetts",
				"USMI","Michigan","USMN","Minnesota","USMS","Mississippi","USMO","Missouri","USMT","Montana",
				"USNE","Nebraska","USNV","Nevada","USNH","New Hampshire","USNJ","New Jersey","USNM","New Mexico",
				"USNY","New York","USNC","North Carolina","USND","North Dakota","USOH","Ohio","USOK","Oklahoma",
				"USOR","Oregon","USPA","Pennsylvania","USRI","Rhode Island","USSC","South Carolina",
				"USSD","South Dakota","USTN","Tennessee","USTX","Texas","USUT","Utah","USVT","Vermont",
				"USVA","Virginia","USWA","Washington","USWV","West Virgina","USWI","Wisconsin","USWY","Wyoming",
				// C A N A D A
				"CAAB","Alberta","CABC","British Columbia","CAMB","Manitoba","CANB","New Brunswick",
				"CANL","Newfoundland and Labrador","CANS","Nova Scotia","CAON","Ontario",
				"CAPE","Prince Edward Island","CAQC","Québec","CASK","Saskatchewan","CAYT","Yukon"
				};
		
		// ISO3166-1 => country
		String i2cNative[] = {"AD","Principat d'Andorra","AE","دولة الإمارات العربية المتحدة","AF","افغانستان",
				"AL","Shqipëri","AM","Հայաստան","AT","Österreich",
				"AO","Repubilika ya Ngola","AS","Amerika Sāmoa","AZ","Azərbaycan",
				"BD","বাংলা","BG","България","BH","البحرين","BN","نڬارا بروني دارالسلام","BR","Brasil","BT","འབྲུག་ཡུལ་","BY","Беларусь",
				"CF","Ködörösêse tî Bêafrîka","CH","Schweiz","CK","Kūki 'Āirani","CN","中国","CZ","Česká republika",
				"CV","Cabo Verde","CY","Κύπρος/Kıbrıs",
				"DE","Deutchland","DJ","جيبوتي","DK","Danmark","DZ","الجزائر‎",
				"EE","Eesti","EG","مصر","ER","ኤርትራ","ES","España","ET","ኢትዮጵያ",
				"FI","Suomi","FJ","Viti","FO","Føroyar","FR","La France",
				"GE","საქართველო","GF","Guyane française","GL","Grönland","GN","Guinée","GR","Ελλάδα","GU","Guåhån",
				"HK","香港","HR","Hrvatska","HT","Ayiti","HU","Magyarország",
				"IE","Erie","IL","מְדִינַת יִשְׂרָאֵל","IN","Bhārat Gaṇarājya","IQ","العراق","IR","ایران","IS","Island","IT","Italia",
				"JP","日本","JO","اَلأُرْدُنّ‎",
				"KG","Кыргызстан","KM","جزر القمر","KP","조선민주주의인민공화국","KR","대한민국","KZ","Қазақстан","KW","دولة الكويت",
				"LA","ສາທາລະນະລັດ ປະຊາທິປະໄຕ ປະຊາຊົນລາວ","LB","لبنان‎","LK","ශ්‍රී ලංකාව","LT","Lietuva","LV","Latvija","LY","‏ليبيا‎",
				"MA","المغرب","ME","Crna Gora","MH","Aolepān Aorōkin M̧ajeļ","MK","Македонија","MN","Монгол улс","MO","澳門","MR","موريتانيا",
				"MV","ދިވެހިރާއްޖެ","MU","Moris",
				"NC","Nouvelle-Calédonie","NL","Nederland","NO","Norge","NP","नेपाल","NU","Niuē","NZ","Aotearoa",
				"OM","عمان",
				"PF","Guyane française","PG","Papua Niugini","PH","Pilipinas","PK","پاکستان","PL","Polska","PS","فلسطين","PW","Beluu ęr a Belau",
				"QA","قطر","RO","România","RS","Република Србија","RU","Россия",
				"SA","السعودية","SD","السودان","SE","Sverige","SI","Slovenija","SK","Slovensko","SN","le Sénégal","SO","Soomaaliya","SZ","Swatini",
				"SY","سوريا",
				"TD","تشاد","TH","ประเทศไทย","TJ","Тоҷикистон","TL","Timór Lorosa'e","TM","Türkmenistan","TN","تونس‎","TR","Türkiye","TW","臺灣",
				"UA","Україна","UZ","O‘zbekiston","VA","Stato della Città del Vaticano",
				"WA","Sāmoa","WF","Uvea mo Futuna","YE","الجمهورية اليمنية"};
		
		// ISO3166-1 => UTC related standard time
		String i2cSTD[] = {"AF","4:30","AL","1:00","DZ","1:00","AS","-11:00","AD","1:00","AO","1:00","AI","-4:00","AG","-4:00",
				"AR","-3:00","AM","4:00","AW","-4:00","AU","10:00","AT","1:00","AZ","4:00",
				"BS","-5:00","BH","3:00","BD","6:00","BB","-4:00","BY","3:00","BE","1:00","BZ","-6:00","BJ","1:00","BM","-4:00","BT","6:00",
				"BO","-4:00","BA","1:00","BW","2:00","BR","-3:00","VG","-4:00","BN","8:00","BG","2:00","BF","0:00","BI","2:00",
				"KH","7:00","CM","1:00","CA","","CV","-1:00","KY","-5:00","CF","1:00","TD","1:00","CL","-4:00","CN","8:00","CO","-5:00",
				"KM","3:00","CG","1:00","CK","-10:00","CR","-6:00","CI","0:00","HR","1:00","CU","-5:00","CW","-4:00","CY","2:00","CZ","1:00","CD","1:00",
				"DK","1:00","DJ","+3:00","DM","-4:00","DO","-4:00",
				"TL","9:00","EC","-5:00","EG","2:00","SV","-6:00","GQ","1:00","ER","3:00","EE","2:00","ET","3:00",
				"FK","-4:00","FO","0:00","FJ","12:00","FI","2:00","FR","1:00","GF","-3:00","PF","-9:00",
				"GA","1:00","GM","0:00","GE","4:00","DE","1:00","GH","0:00","GI","1:00","GR","2:00","GL","0:00","GD","-4:00","GP","-4:00",
				"GU","10:00","GT","-6:00","GN","0:00","GW","0:00","GY","-4:00",
				"HT","-5:00","HN","-6:00","HK","8:00","HU","1:00",
				"IS","0:00","IN","5:30","ID","7:00","IR","3:30","IQ","3:00","IE","0:00","IL","2:00","IT","1:00",
				"JM","-5:00","JP","9:00","JO","2:00",
				"KZ","5:00","KE","3:00","KI","12:00","KP","9:00","KR","9:00","KW","3:00","KG","6:00",
				"LA","7:00","LV","2:00","LB","2:00","LS","2:00","LR","0:00","LY","2:00","LI","1:00","LT","2:00","LU","1:00",
				"MO","8:00","MK","1:00","MG","3:00","MW","2:00","MY","8:00","MV","5:00","ML","0:00","MT","1:00","MH","12:00","MQ","-4:00","MR","0:00",
				"MU","4:00","MX","-6:00","FM","11:00","MD","2:00","MC","1:00","MN","8:00","ME","1:00","MS","-4:00","MA","0:00","MZ","2:00","MM","6:30",
				"NA","1:00","NR","12:00","NP","5:45","NL","1:00","NC","11:00","NZ","12:00","NI","-6:00","NE","1:00","NG","1:00","NU","-11:00",
				"MP","10:00","NO","1:00",
				"OM","4:00",
				"PK","5:00","PW","9:00","PA","-4:00","PG","10:00","PY","-3:00","PE","-5:00","PH","8:00","PL","1:00","PT","0:00","PR","-4:00",
				"QA","3:00",
				"RE","4:00","RO","2:00","RU","4:00","RW","2:00",
				"KN","-4:00","LC","-4:00","PM","-3:00","VC","-4:00","WS","13:00","SM","1:00","ST","0:00","SA","3:00","SN","0:00","RS","1:00",
				"SC","4:00","SL","0:00","SG","8:00","SK","1:00","SI","1:00","SB","11:00","SO","3:00","ZA","2:00","ES","1:00","LK","5:30",
				"SD","3:00","SR","-3:00","SZ","2:00","SE","1:00","CH","1:00","SY","2:00",
				"TW","8:00","TJ","5:00","TZ","3:00","TH","7:00","TG","0:00","TO","13:00","TT","-4:00","TN","1:00","TR","2:00","TM","5:00","TC","-5:00",
				"UG","3:00","UA","2:00","AE","4:00","GB","0:00","US","","VI","-4:00","UY","-3:00","UZ","5:00",
				"VU","11:00","VA","1:00","VE","-4:30","VN","7:00",
				"WF","12:00",
				"YE","3:00",
				"ZM","2:00","ZW","2:00",
				// U S A
				"USAL","-6:00","USAK","-9:00","USAZ","-7:00,","USAR","-6:00","USCA","-8:00","USCO","-7:00","USCT","-5:00",
				"USDE","-5:00","USDC","-5:00","USFL","-5:00","USGA","-5:00","USHI","-10:00","USID","-7:00","USIL","-6:00",
				"USIN","-5:00","USIA","-6:00","USKS","-6:00","USKY","-5:00","USLA","-6:00","USME","-5:00","USMD","-5:00",
				"USMA","-5:00","USMI","-5:00","USMN","-6:00","USMS","-6:00","USMO","-6:00","USMT","-7:00","USNE","-6:00",
				"USNV","-8:00","USNH","-5:00","USNJ","-5:00","USNM","-7:00","USNY","-5:00","USNC","-5:00","USND","-6:00",
				"USOH","-5:00","USOK","-6:00","USOR","-8:00","USPA","-5:00","USRI","-5:00","USSC","-5:00","USSD","-7:00",
				"USTN","-6:00","USTX","-6:00","USUT","-7:00","USVT","-5:00","USVA","-5:00","USWA","-8:00","USWV","-5:00",
				"USWI","-6:00","USWY","-7:00",
				// C A N A D A
				"CAAB","-7:00","CABC","-8:00","CAMB","-6:00","CANB","-4:00","CANL","-3:30","CANS","-4:00","CAON","-5:00",
				"CAPE","-4:00","CAQC","-5:00","CASK","-6:00","CAYT","-8:00"};

		// ISO3166-1 => DST offset
		String i2cDST[] = {"AF","0","AL","1","DZ","0","AS","0","AD","1","AO","0","AI","0","AG","0","AR","0","AM","0","AW","0","AU","1",
				"AT","1","AZ","1",
				"BS","1","BH","0","BD","0","BB","0","BY","0","BE","1","BZ","0","BJ","0","BM","1","BT","0","BO","0","BA","1","BW","0","BR","0",
				"VG","0","BN","0","BG","1","BF","0","BI","0",
				"KH","0","CM","0","CA","1","CV","0","KY","0","CF","0",
				"TD","0","CL","1","CN","0","CO","0","KM","0","CG","0","CK","0","CR","0","CI","0","HR","1","CU","1","CW","0","CY","1","CZ","1","CD","0",
				"DK","1","DJ","0","DM","0","DO","0",
				"TL","0","EC","0","EG","0","SV","0","GQ","0","ER","0","EE","1","ET","0",
				"FK","1","FO","1","FJ","0","FI","1","FR","1","GF","0","PF","0",
				"GA","0","GM","0","GE","0","DE","1","GH","0","GI","1","GR","1","GL","0","GD","0","GP","0","GU","0","GT","0","GN","0","GW","0","GY","0",
				"HT","1","HN","0","HK","0","HU","1",
				"IS","0","IN","0","ID","0","IR","0","IQ","0","IE","1","IL","0","IT","1",
				"JM","0","JP","0","JO","1",
				"KZ","0","KE","0","KI","0","KP","0","KR","0","KW","0","KG","0",
				"LA","0","LV","1","LB","1","LS","0","LR","0","LY","0","LI","1","LT","1","LU","1",
				"MO","0","MK","1","MG","0","MW","0","MY","0","MV","0","ML","0","MT","1","MH","0","MQ","0","MR","0","MU","0","MX","1","FM","0","MD","1",
				"MC","1","MN","0","ME","1","MS","0","MA","0","MZ","0","MM","0",
				"NA","1","NR","0","NP","0","NL","1","NC","0","NZ","1","NI","0",
				"NE","0","NG","0","NU","0","MP","0","NO","1",
				"OM","0",
				"PK","0","PW","0","PA","0","PG","0","PY","1","PE","0","PH","0","PL","1","PT","1","PR","0",
				"QA","0",
				"RE","0","RO","1","RU","0","RW","0",
				"KN","0","LC","0","PM","1","VC","0","WS","1","SM","1","ST","0","SA","0","SN","0","RS","1","SC","0","SL","0","SG","0","SK","1",
				"SI","1","SB","0","SO","0","ZA","0","ES","1","LK","0","SD","0","SR","0","SZ","0","SE","1","CH","1","SY","1",
				"TW","0","TJ","0","TZ","0","TH","0","TG","0","TO","0","TT","0","TN","0","TR","1","TM","0","TC","1",
				"UG","0","UA","1","AE","0","GB","1","VI","0","UY","1","UZ","0",
				"VU","0","VA","1","VE","0","VN","0",
				"WF","0",
				"YE","0",
				"ZM","0","ZW","0",
				// U S A
				"USAL","1","USAK","1","USAZ","0","USAR","1","USCA","1","USCO","1","USCT","1","USDE","1","USDC","1","USFL","1",
				"USGA","1","USHI","0","USID","1","USIL","1","USIN","1","USIA","1","USKS","1","USKY","1","USLA","1","USME","1",
				"USMD","1","USMA","1","USMI","1","USMN","1","USMS","1","USMO","1","USMT","1","USNE","1","USNV","1","USNH","1",
				"USNJ","1","USNM","1","USNY","1","USNC","1","USND","1","USOH","1","USOK","1","USOR","1","USPA","1","USRI","1",
				"USSC","1","USSD","1","USTN","1","USTX","1","USUT","1","USVT","1","USVA","1","USWA","1","USWV","1","USWI","1",
				"USWY","1",
				// C A N A D A
				"CAAB","1","CABC","1","CAMB","1","CANB","1","CANL","1","CANS","1","CAON","1","CAPE","1","CAQC","1","CASK","0",
				"CAYT","1"};
		
		for  (int i=0; i<p2i.length; i++) { prefix2iso.put(Integer.parseInt(p2i[i]),p2i[++i]); }
		
		for  (int i=0; i<i2cEn.length; i++) { ISOmcc2country.put(i2cEn[i],i2cEn[++i]); }
		
		for  (int i=0; i<i2cNative.length; i++) { ISOmcc2countryN.put(i2cNative[i],i2cNative[++i]); }
		
		for  (int i=0; i<i2cSTD.length; i++) { ISOmcc2STD.put(i2cSTD[i],i2cSTD[++i]); }

		for  (int i=0; i<i2cDST.length; i++) { ISOmcc2DSToffset.put(i2cDST[i],Integer.parseInt(i2cDST[++i])); }

	}
	
	public void put(Integer p, String c) {
		prefix2iso.put(p,c);
	}

	/*
	 * Returns country ISO mcc based on calling prefix
	 */
	public String getISOmcc(Integer p) {
	
		String i = null;
		if ( prefix2iso.get(p) != null ) { i = prefix2iso.get(p); }
		
		return i;
	}
	
	/*
	 * Returns country name based on country calling prefix
	 */
 	public String getcountry(final Integer p) {
 		String iso = "";
 		
		if ( prefix2iso.get(p) != null ) { 
			iso = prefix2iso.get(p);
			if ( getcountry(iso) != null ) { return getcountry(iso); }
		}
		return "";
	}

 	/*
 	 * Returns country name based on ISO mcc
 	 */
	public String getcountry(final String iso) {
		
		String txt = "";
		String txtN = "";
		if ( ISOmcc2country.get(iso) != null ) {
			txt = ISOmcc2country.get(iso);
			if ( ISOmcc2countryN.get(iso) != null ) { 
					txtN = ISOmcc2countryN.get(iso);
					if ( txt != txtN ) { txt = txtN + "/" + txt; }
			}
			// NANP
			if ( iso.substring(0,2).equals("US") || iso.substring(0,2).equals("CA") ) {
				txt = txt + ", " + iso.substring(2,4);
			}
		}
		return txt;
	}
	
	/*
     * Returns local time for country denoted by ISO MCC code argument
     */
    public String getLocalTime(String ISOmcc, String myISO) { // throws ParseException {
    	Boolean err = false;
    	
    	if ( ISOmcc.equals(myISO) ) { return ""; }
    	
    	Time myUTC = new Time();
    	myUTC.setToNow();
    	
    	String[] tstr;
    	
    	Calendar c = Calendar.getInstance();	// my local time with local DST considered
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    	if ( ISOmcc2STD.get(myISO) != null ) {
    		tstr = ISOmcc2STD.get(myISO).split(":");
    		c.add(Calendar.HOUR, (-1) * Integer.parseInt(tstr[0]));
    		c.add(Calendar.MINUTE, (-1) * Integer.parseInt(tstr[1]));    		
    		// c <= my local UTC time with local DST
    	} else { err = true; }
    	
    	//Log.v("getLocalTime","after myUTC: " + myISO + ": " + sdf.format(c.getTime()));
    	
    	if ( ISOmcc2STD.get(ISOmcc) != null ) {
    		tstr = ISOmcc2STD.get(ISOmcc).split(":");
       		c.add(Calendar.HOUR, Integer.parseInt(tstr[0]));
    		c.add(Calendar.MINUTE, Integer.parseInt(tstr[1]));    	
    		// c <= remote time in UTC (no DST considered yet for remote location
    	} else { err = true; }
    	//Log.v("getLocalTime","after remoteUTC: " + sdf.format(c.getTime()));

    	// Log.v("getLocalTime","before DST: " + Boolean.toString(err));

    	String s = sdf.format(c.getTime());
    	// Log.d("getLocalTime","before DST: " + s);
    	
    	if ( ! err ) {    		
    		if ( myUTC.isDst > 0 ) { // in DST
       			if ( ISOmcc2DSToffset.get(myISO) != null ) { c.add(Calendar.HOUR, (-1) * ISOmcc2DSToffset.get(myISO)); }
    			
    			if ( ISOmcc2DSToffset.get(ISOmcc) != null ) { c.add(Calendar.HOUR, ISOmcc2DSToffset.get(ISOmcc)); }
    		}
            s = sdf.format(c.getTime());
    	}    	
    	return s; 
    }
}
