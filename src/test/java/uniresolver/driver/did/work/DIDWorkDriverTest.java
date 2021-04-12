package uniresolver.driver.did.work;


import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Test;

import foundation.identity.did.DIDDocument;
import uniresolver.ResolutionException;
import uniresolver.result.ResolveResult;

import static junit.framework.TestCase.assertEquals;

public class DIDWorkDriverTest {

    @Test
    public void testParsingV1Doc() throws Exception {
        DIDWorkDriver didWorkDriver = new LocalDIDWorkDriver();

        ResolveResult result = didWorkDriver.resolve(v1Did);
        assertEquals(v1Expected, result.toJson());
    }

    @Test
    public void testParsingV2TransitionDoc() throws Exception {
        DIDWorkDriver didWorkDriver = new LocalDIDWorkDriver();

        ResolveResult result = didWorkDriver.resolve(v2TransitionDid);
        assertEquals(v2TransitionExpected, result.toJson());
    }

    @Test
    public void testParsingV2Doc()  throws Exception {
        DIDWorkDriver didWorkDriver = new LocalDIDWorkDriver();

        ResolveResult result = didWorkDriver.resolve(v2Did);
        assertEquals(v2Expected, result.toJson());
        assertEquals(DIDDocument.MIME_TYPE_JSON_LD, result.getContentType());
    }

    class LocalDIDWorkDriver extends DIDWorkDriver{
        Map<String, String> didMap  = new HashMap<>();
        LocalDIDWorkDriver(){
            didMap.put(v1Did, v1DIDWorkJson);
            didMap.put(v2TransitionDid, v2TransitionDIDWorkJson);
            didMap.put(v2Did, v2DIDWorkJson);
        }
        @Override
        String getDocumentFromWork(String identifier) throws Exception {
            return didMap.get(identifier);
        }

    }
    String v1Did = "did:work:2UUHQCd4psvkPLZGnWY33L";
    String v2TransitionDid = "did:work:Fbu3wysRMx1si4poarqfKs";
    String v2Did = "did:work:9rB9n9ZeKeU1bvkYdATCKE";
    String v1DIDWorkJson = "{\"@context\":\"https://w3id.org/did/v1\",\"id\":\"did:work:2UUHQCd4psvkPLZGnWY33L\",\"publicKey\":[{\"id\":\"did:work:2UUHQCd4psvkPLZGnWY33L#key-1\",\"type\":\"Ed25519VerificationKey2018\",\"controller\":\"did:work:4PUfQhXdyRY4LLNkKUs16S\",\"publicKeyBase58\":\"oatUtA5A97kQ7mWYK8qHkVFvJKtnhRdBZPSwuC3dADw\"}],\"authentication\":null,\"service\":[{\"id\":\"did:work:8RcWPSBtB4QwfC68yneDxC;id=860285e2-183d-4fe3-9767-babc744396b8;version=1.0\",\"type\":\"schema\",\"serviceEndpoint\":\"did:work:8RcWPSBtB4QwfC68yneDxC;id=860285e2-183d-4fe3-9767-babc744396b8;version=1.0\"}],\"proof\":{\"created\":\"2019-09-28T18:36:40Z\",\"creator\":\"did:work:2UUHQCd4psvkPLZGnWY33L#key-1\",\"nonce\":\"9302b4c1-1242-44b1-bb7d-761878ea3228\",\"signatureValue\":\"3vvzcpns5j4mq2fGsGUE1kntnFKQaAm7YcBBr3z9keRtAKC3ioYiWzNdTkyysQ9oiKMd1CNi25BUNdzLKFT647MX\",\"type\":\"Ed25519VerificationKey2018\"}}\n";
    String v1Expected = "{\"didDocument\":{\"@context\":\"https://w3id.org/did/v1\",\"id\":\"did:work:2UUHQCd4psvkPLZGnWY33L\",\"publicKey\":[{\"id\":\"did:work:2UUHQCd4psvkPLZGnWY33L#key-1\",\"type\":\"Ed25519VerificationKey2018\",\"controller\":\"did:work:4PUfQhXdyRY4LLNkKUs16S\",\"publicKeyBase58\":\"oatUtA5A97kQ7mWYK8qHkVFvJKtnhRdBZPSwuC3dADw\"}],\"authentication\":null,\"service\":[{\"id\":\"did:work:8RcWPSBtB4QwfC68yneDxC;id=860285e2-183d-4fe3-9767-babc744396b8;version=1.0\",\"type\":\"schema\",\"serviceEndpoint\":\"did:work:8RcWPSBtB4QwfC68yneDxC;id=860285e2-183d-4fe3-9767-babc744396b8;version=1.0\"}]},\"content\":null,\"contentType\":\"application/did+ld+json\",\"didResolutionMetadata\":{},\"didDocumentMetadata\":{}}";
    String v2TransitionDIDWorkJson = "{\"id\":\"did:work:Fbu3wysRMx1si4poarqfKs\",\"publicKey\":[{\"id\":\"did:work:Fbu3wysRMx1si4poarqfKs#key-1\",\"type\":\"Ed25519VerificationKey2018\",\"controller\":\"did:work:Fbu3wysRMx1si4poarqfKs\",\"publicKeyBase58\":\"8xb6eC7ujFcFgyuv6JbuAp9ZG5vu38J5HopfBySaiKqr\"}],\"authentication\":null,\"service\":null,\"verificationMethod\":[{\"id\":\"did:work:Fbu3wysRMx1si4poarqfKs#key-1\",\"type\":\"Ed25519VerificationKey2018\",\"controller\":\"did:work:Fbu3wysRMx1si4poarqfKs\",\"publicKeyBase58\":\"8xb6eC7ujFcFgyuv6JbuAp9ZG5vu38J5HopfBySaiKqr\"}]}";
    String v2TransitionExpected = "{\"didDocument\":{\"id\":\"did:work:Fbu3wysRMx1si4poarqfKs\",\"publicKey\":[{\"id\":\"did:work:Fbu3wysRMx1si4poarqfKs#key-1\",\"type\":\"Ed25519VerificationKey2018\",\"controller\":\"did:work:Fbu3wysRMx1si4poarqfKs\",\"publicKeyBase58\":\"8xb6eC7ujFcFgyuv6JbuAp9ZG5vu38J5HopfBySaiKqr\"}],\"authentication\":null,\"service\":null,\"verificationMethod\":[{\"id\":\"did:work:Fbu3wysRMx1si4poarqfKs#key-1\",\"type\":\"Ed25519VerificationKey2018\",\"controller\":\"did:work:Fbu3wysRMx1si4poarqfKs\",\"publicKeyBase58\":\"8xb6eC7ujFcFgyuv6JbuAp9ZG5vu38J5HopfBySaiKqr\"}]},\"content\":null,\"contentType\":\"application/did+ld+json\",\"didResolutionMetadata\":{},\"didDocumentMetadata\":{}}";
    String v2DIDWorkJson = "{\"@context\":\"https://w3id.org/did/v1\",\"id\":\"did:work:9rB9n9ZeKeU1bvkYdATCKE\",\"authentication\":null,\"service\":null,\"verificationMethod\":[{\"id\":\"did:work:9rB9n9ZeKeU1bvkYdATCKE#key-1\",\"type\":\"Ed25519VerificationKey2018\",\"controller\":\"did:work:9rB9n9ZeKeU1bvkYdATCKE\",\"publicKeyBase58\":\"5phUW3xquqbxHMvqsXg3udgDvdjKqjnQaWCfnTHMf9WT\"}]}";
    String v2Expected = "{\"didDocument\":{\"@context\":\"https://w3id.org/did/v1\",\"id\":\"did:work:9rB9n9ZeKeU1bvkYdATCKE\",\"authentication\":null,\"service\":null,\"verificationMethod\":[{\"id\":\"did:work:9rB9n9ZeKeU1bvkYdATCKE#key-1\",\"type\":\"Ed25519VerificationKey2018\",\"controller\":\"did:work:9rB9n9ZeKeU1bvkYdATCKE\",\"publicKeyBase58\":\"5phUW3xquqbxHMvqsXg3udgDvdjKqjnQaWCfnTHMf9WT\"}]},\"content\":null,\"contentType\":\"application/did+ld+json\",\"didResolutionMetadata\":{},\"didDocumentMetadata\":{}}";
}