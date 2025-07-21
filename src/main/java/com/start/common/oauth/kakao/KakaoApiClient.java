package com.start.common.oauth.kakao;

@Component
@RequiredArgsConstructor
public class KakaoApiClient implements OAuthApiClient {
    private static final String GRANT_TYPE = "authorization_code";
    private final RestTemplate restTemplate;

    @Value("${spring.oauth.kakao.url.auth}")
    private String authUrl;

    @Value("${spring.oauth.kakao.url.api}")
    private String apiUrl;

    @Value("${spring.oauth.kakao.client-id}")
    private String clientId;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.KAKAO;
    }

    @Override
    public KakaoTokenResponse requestAccessToken(OAuthLoginParams params) {
        String url = authUrl + "/oauth/token";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = params.makeBody();
        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", clientId);

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        KakaoTokens response = restTemplate.postForObject(url, request, KakaoTokens.class);

        assert response != null;
        return new KakaoTokenResponse(response.getAccessToken(), response.getRefreshToken());
    }

    @Override
    public OAuthInfoResponse requestOauthInfo(String accessToken) {
        String url = apiUrl + "/v2/user/me";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"kakao_account.email\", \"kakao_account.profile\", \"kakao_account.gender\", \"kakao_account.age_range\", \"kakao_account.birthday\"]");

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        return restTemplate.postForObject(url, request, KakaoInfoResponse.class);
    }
}
