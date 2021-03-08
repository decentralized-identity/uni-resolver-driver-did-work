package uniresolver.driver.did.work;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;

import foundation.identity.did.DIDDocument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uniresolver.ResolutionException;
import uniresolver.driver.Driver;
import uniresolver.result.ResolveResult;

public class DIDWorkDriver implements Driver {

	private static Logger log = LoggerFactory.getLogger(DIDWorkDriver.class);

	private final Pattern DID_WORK_PATTERN = Pattern.compile("^did:work:.+");

	private String WORK_DOMAIN;
	private String API_KEY;

	public DIDWorkDriver() {
		this.getPropertiesFromEnvironment();
	}

	private void getPropertiesFromEnvironment() {
		if (log.isDebugEnabled()) log.debug("Loading from environment: " + System.getenv());
		try {
			this.setWorkDomain(System.getenv("uniresolver_driver_did_work_domain"));
			this.setApiKey(System.getenv("uniresolver_driver_did_work_apikey"));
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}
	String getDocumentFromWork(String identifier) throws Exception {
		HttpClient client = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
		HttpGet httpGet = new HttpGet(WORK_DOMAIN + "/v1/did/" + identifier);
		httpGet.setHeader("x-api-key", API_KEY);

		CloseableHttpResponse httpResponse = (CloseableHttpResponse) client.execute(httpGet);
		if(httpResponse.getStatusLine().getStatusCode() != 200)
			throw new Exception("Cannot retrieve DIDDocument for DID " + identifier);

		HttpEntity httpEntity = httpResponse.getEntity();
		String entityString = EntityUtils.toString(httpEntity);
		return entityString;
	}


	@Override
	public ResolveResult resolve(String identifier) throws ResolutionException {
		if (log.isDebugEnabled()) log.debug("Resolving identifier " + identifier);

		// match
		Matcher matcher = DID_WORK_PATTERN.matcher(identifier);
		if (!matcher.find()) {
			return null;
		}

		DIDDocument didDoc;
		try {
			String returnedDocument = getDocumentFromWork(identifier);
			didDoc = DIDDocument.fromJson(returnedDocument);
		} catch (Exception e) {
			throw new ResolutionException(e);
		}
		ResolveResult result = ResolveResult.build(didDoc);
		result.setContentType(DIDDocument.MIME_TYPE_JSON_LD);
		return result;
	}

	@Override
	public Map<String, Object> properties() {
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("workEndpoint", WORK_DOMAIN);
		return props;
	}

	public void setWorkDomain(String workDomain) {
		this.WORK_DOMAIN = workDomain;
	}

	public void setApiKey(String apiKey) {
		this.API_KEY = apiKey;
	}
}
