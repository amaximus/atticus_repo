package com.atticus.CallMap;

import java.util.HashMap;
import java.util.Map;

import android.util.SparseArray;

public class Country_Code {
	
	//Map<Integer, String> prefix2country = new HashMap<Integer, String>();
	SparseArray<String> prefix2iso = new SparseArray<String>();

	Map<String, String> ISOmcc2country = new HashMap<String, String>();
	Map<String, String> ISOmcc2countryN = new HashMap<String, String>();


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
				"970","PS","971","AE","972","IL","973","BH","974","QA","975","BT","976","MN","977","NP",
				"992","TJ","993","TM","994","AZ","995","GE","996","KG","998","UZ",
				"1242","BS","1246","BB","1264","AI","1268","AG","1284","VG","1340","VI","1345","KY","1441","BM","1473","GD",
				"1649","TC","1664","MS","1670","MP","1671","GU","1684","AS",
				"1721","SX","1758","LC","1767","DM","1784","VC","1787","PR",
				"1809","DO","1868","TT","1869","KN","1876","JM","1939","PR"
				};
		
		// ISO3166-1 => country
		String i2cEn[] = {"AF","Afghanistan","AL","Albania","DZ","Algeria","AS","American Samoa","AD","Andorra","AO","Angola",
				"AI","Anguilla","AG","Antigua and Barbuda","AR","Argentine Republic","AM","Armenia","AW","Aruba","AU","Australia",
				"AT","Austria","AZ","Azerbaijani Republic",
				"BS","Bahamas","BH","Bahrain","BD","Bangladesh","BB","Barbados","BY","Belarus","BE","Belgium","BZ","Belize",
				"BJ","Benin","BM","Bermuda","BT","Bhutan","BO","Bolivia","BA","Bosna i Hercegovina","BW","Botswana","BR","Brazil",
				"VG","British Virgin Islands","BN","Brunei Darussalam","BG","Bulgaria","BF","Burkina Faso","BI","Burundi",
				"KH","Cambodia","CM","Cameroon","CA","Canada","CV","Cape Verde","KY","Cayman Islands","CF","Central African Republic",
				"TD","Chad","CL","Chile","CN","China","CO","Colombia","KM","Comoros","CG","Republique du Congo","CK","Cook Islands",
				"CR","Costa Rica","CI","Côte d'Ivoire","HR","Croatia","CU","Cuba","CW","Curaçao","CY","Cyprus","CZ","Czech Republic",
				"CD","Congo",
				"DK","Denmark","DJ","Djibouti","DM","Dominica","DO","República Dominicana",
				"TL","East Timor","EC","Ecuador","EG","Egypt","SV","El Salvador","GQ","Guinea Ecuatorial","ER","Eritrea","EE","Estonia",
				"ET","Ethiopia",
				"FK","Falkland Islands","FO","Faroe Islands","FJ","Fiji","FI","Finland","FR","France","GF","French Guiana",
				"PF","French Polynesia",
				"GA","Gabon","GM","Gambia","GE","Georgia","DE","Germany","GH","Ghana","GI","Gibraltar","GR","Greece","GL","Greenland",
				"GD","Grenada","GP","Guadeloupe","GU","Guam","GT","Guatemala","GN","Guinea","GW","Guinea-Bissau","GY","Guyana",
				"HT","Haiti","HN","Honduras","HK","Hong Kong","HU","Hungary",
				"IS","Iceland","IN","India","IN","India","ID","Indonesia","IR","Iran","IQ","Iraq","IE","Ireland","IL","Israel","IT","Italy",
				"JM","Jamaica","JP","Japan","JO","Jordan",
				"KZ","Kazakhstan","KE","Kenya","KI","Kiribati","KP","North Korea","KR","South Korea","KW","Kuwait","KG","Kyrgyz Republic",
				"LA","Laos","LV","Latvia","LB","Lebanon","LS","Lesotho","LR","Liberia","LY","Libya","LI","Liechtenstein","LT","Lithuania",
				"LU","Luxembourg",
				"MO","Macau","MK","Macedonia","MG","Madagascar","MW","Malawi","MY","Malaysia","MV","Maldives","ML","Mali","MT","Malta",
				"MH","Marshall Islands","MQ","Martinique","MR","Mauritania","MU","Mauritius","MX","México","FM",
				"Federated States of Micronesia","MD","Moldova","MC","Monaco","MN","Mongolia","ME","Montenegro","MS","Montserrat",
				"MA","Morocco","MZ","Mozambique","MM","Myanmar",
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
				"ZM","Zambia","ZW","Zimbabwe"};
		
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
		
		for  (int i=0; i<p2i.length; i++) { prefix2iso.put(Integer.parseInt(p2i[i]),p2i[++i]); }
		
		for  (int i=0; i<i2cEn.length; i++) { ISOmcc2country.put(i2cEn[i],i2cEn[++i]); }
		
		for  (int i=0; i<i2cNative.length; i++) { ISOmcc2countryN.put(i2cNative[i],i2cNative[++i]); }

	}
	
	public void put(Integer p, String c) {
		prefix2iso.put(p,c);
	}

 	public String getcountry(final Integer p) {
 		String iso = "";
 		
		if ( prefix2iso.get(p) != null ) { 
			iso = prefix2iso.get(p);
			if ( getcountry(iso) != null ) { return getcountry(iso); }
		}
		return "";
	}

	public String getcountry(final String iso) {
		
		String txt = "";
		String txtN = "";
		if ( ISOmcc2country.get(iso) != null ) {
			txt = ISOmcc2country.get(iso);
			if ( ISOmcc2countryN.get(iso) != null ) { 
					txtN = ISOmcc2countryN.get(iso);
					if ( txt != txtN ) { txt = txtN + "/" + txt; }
			}
		}
		return txt;
	}
}
