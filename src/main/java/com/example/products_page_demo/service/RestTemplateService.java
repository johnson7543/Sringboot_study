package com.example.products_page_demo.service;

import com.example.products_page_demo.model.Products;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestTemplateService {
    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    public List<Products> getFromHktvApi() throws JsonProcessingException, JSONException {
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "https://www.hktvmall.com/hktv/zh/ajax/getTop100?code=personalcarenhealth&pageSize=11&currentPage=0";
        Object temp = restTemplate.getForObject(url, Object.class);
        String jsonString = objectMapper.writeValueAsString(temp);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("products");

//        String regEx_style = "(?is)<style.*?>.*?</style>";  // 定義style的正則表示式
//        String regEx_br = "\\<br[^>]*>(?i)";  // 定義br的正則表示式
        String regEx_html = "(?is)<.*?>";  // 定義HTML標籤的正則表示式
        String regEx_space = "\\s*|\t|\r|\n";  //定義空格換行符

        List<Products> productsList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject explrObject = jsonArray.getJSONObject(i);
            Products tempPorduct = new Products();
            String descriptionWithHtmlLabel = explrObject.getString("description");  // products.description
//            Pattern pstyle = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
//            Matcher mstyle = pstyle.matcher(descriptionWithHtmlLabel);
//            descriptionWithHtmlLabel = mstyle.replaceAll(""); // 過濾style標籤
//            Pattern pBr = Pattern.compile(regEx_br, Pattern.CASE_INSENSITIVE);
//            Matcher mBr = pBr.matcher(descriptionWithHtmlLabel);
//            descriptionWithHtmlLabel = mBr.replaceAll(""); // 過濾br標籤
            Pattern pHtml = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            Matcher mHtml = pHtml.matcher(descriptionWithHtmlLabel);
            descriptionWithHtmlLabel = mHtml.replaceAll(""); // 過濾html標籤
            Pattern pSpace = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
            Matcher mSpace = pSpace.matcher(descriptionWithHtmlLabel);
            descriptionWithHtmlLabel = mSpace.replaceAll(""); // 過濾空格換行標籤

            tempPorduct.setCode(explrObject.getString("code"));
            tempPorduct.setName(explrObject.getString("name"));
            tempPorduct.setDescription(descriptionWithHtmlLabel);
            productsList.add(tempPorduct);
        }  // for ()

        return productsList;
    }
}
