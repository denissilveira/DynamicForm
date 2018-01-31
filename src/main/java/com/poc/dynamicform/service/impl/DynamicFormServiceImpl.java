package com.poc.dynamicform.service.impl;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.poc.dynamicform.converter.DynamicFormEntityToForm;
import com.poc.dynamicform.domain.entity.DynamicElement;
import com.poc.dynamicform.domain.entity.DynamicField;
import com.poc.dynamicform.domain.entity.DynamicForm;
import com.poc.dynamicform.domain.entity.DynamicGroup;
import com.poc.dynamicform.repository.DynamicElementRepository;
import com.poc.dynamicform.repository.DynamicFormRepository;
import com.poc.dynamicform.repository.DynamicOptionRepository;
import com.poc.dynamicform.service.DynamicFormService;
import com.poc.dynamicform.web.form.Field;
import com.poc.dynamicform.web.form.Form;
import com.poc.dynamicform.web.form.Group;
import com.poc.dynamicform.web.form.Option;


@Service
public class DynamicFormServiceImpl implements DynamicFormService {
	
	@Autowired
	private DynamicFormRepository dfRepository;
	@Autowired
	private DynamicOptionRepository doRepository;
	@Autowired
	private DynamicFormEntityToForm formConverter;
	@Autowired
    private DynamicElementRepository deRepository;

	public Form loadForm(final Long id) throws Exception {

        final DynamicForm form = dfRepository.findOne(id);
        if (form == null)
            throw new Exception("Formulário não encontrado!");
        
        final List<DynamicElement> dynamicElementsDB = deRepository.findByDynamicFormAndParentIsNullOrderByPosition(form);
        if (CollectionUtils.isEmpty(dynamicElementsDB))
            throw new Exception("Este Formulário não possui elementos!");
        
        final List<DynamicElement> elements = new ArrayList<DynamicElement>();
        dynamicElementsDB.forEach(element -> {
            
            if(element instanceof DynamicGroup) {
                element.setElements(getSubElements(element));
                elements.add(element);
            } else  if(element instanceof DynamicField) {
                final DynamicField dynamicField = (DynamicField) element;
                dynamicField.setOptions(doRepository.findByDynamicField(dynamicField));
                elements.add(dynamicField);
            }
        });
        
        form.setElements(elements);
        
        
        Form rForm = formConverter.convert(form);
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(false);
        DocumentBuilder docBuilder;
        docBuilder = dbf.newDocumentBuilder();
        //Document xmlDocument = docBuilder.parse(new File("/home/jsena/CCP2018_ANEXO2.xml"));
        //setFieldValue(rForm.getElements(),xmlDocument,"");
        
        return rForm;
    }
    
    private List<DynamicElement> getSubElements(final DynamicElement parent) {
        
        final List<DynamicElement> children = deRepository.findByParentOrderByPosition(parent);
        
        if (!CollectionUtils.isEmpty(children)) {
            children.forEach(element -> {
                if(element instanceof DynamicGroup) {
                    element.setElements(getSubElements(element));
                } else  if(element instanceof DynamicField) {
                    final DynamicField dynamicField = (DynamicField) element;
                    dynamicField.setOptions(doRepository.findByDynamicField(dynamicField));
                    dynamicField.setElements(getSubElements(element));
                }
            });
        }
        return children;
    }
    
    /**
     * 
     * @param form
     * @param xmlDocument
     */
    private void setFieldValue(List<com.poc.dynamicform.web.form.Element> elements, Document xmlDocument, String xpath) {
        for(com.poc.dynamicform.web.form.Element element : elements){
            if(element instanceof Group) {
                setFieldValue(element.getElements(),xmlDocument,xpath+"//"+((Group) element).getName());
            } else  if(element instanceof Field) {
                if(((Field) element).isContainsSubForm()){
                    setSubGroupValues(xmlDocument, xpath, element);
                    setFieldValue(element.getElements(),xmlDocument,xpath);
                }else{
                    String value = getXMLContent(xmlDocument,xpath+"/"+((Field) element).getName());
                    ((Field) element).setValue(value);
                    for(Option opt : ((Field) element).getOptions() ){
                        opt.setSelectedValue(value);
                    }
                }
            }
        }
    }
    
    /**
     * 
     * @param xmlDocument
     * @param xpath
     * @param element
     */
    private void setSubGroupValues(Document xmlDocument, String xpath, com.poc.dynamicform.web.form.Element element) {
        String firstChild = getFirstChild(xmlDocument,xpath);
        for(Option opt : ((Field) element).getOptions() ){
            opt.setSelectedValue(firstChild);
        }
        for(com.poc.dynamicform.web.form.Element subElement : element.getElements()){
            if(subElement.getName().equals(firstChild)){
                subElement.setShow(true);
            }
        }
    }
    
    /**
     * 
     * @param xmlDocument
     * @param xpath
     * @return
     */
    private String getFirstChild(Document xmlDocument, String xpath) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node;
        String content = null;
        try {
            node = (Node) xPath.compile(xpath).evaluate(xmlDocument, XPathConstants.NODE);
            content = node!=null ? node.getFirstChild().getNextSibling().getNodeName() : null;
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 
     * @param xmlDocument
     * @param xpath
     * @return
     */
    private String getXMLContent(Document xmlDocument, String xpath) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node;
        String content = null;
        try {
            node = (Node) xPath.compile(xpath).evaluate(xmlDocument, XPathConstants.NODE);
            content = node!=null ? node.getTextContent() : null;
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return content;
    }
    
    /**
     * 
     * @param form
     * @param dataJson
     */
    public void generateXML(com.poc.dynamicform.web.form.Form form,HashMap<String, Object> dataMap){
        
           try {
               
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;
            docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(form.getName());
            doc.appendChild(rootElement);
            
            Element eGroup = doc.createElement(form.getVersion());
            rootElement.appendChild(eGroup);

            formatElement(form.getElements(), doc, eGroup,dataMap,false,0);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StringWriter stringWriter = new StringWriter();
            StreamResult result = new StreamResult(stringWriter);
            transformer.transform(source, result);
            System.out.println("XML--->" + stringWriter.toString());

           } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
        
    }


    /**
     * 
     * @param elements
     * @param doc
     * @param rootElement
     * @param dataMap
     * @param isSubGroup
     * @param rowsIndex
     */
    private void formatElement(List<com.poc.dynamicform.web.form.Element> elements, Document doc, Element rootElement, HashMap<String, Object> dataMap, Boolean isSubGroup,int rowsIndex){
        
        Map<String,Object> result = dataMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        
        for(com.poc.dynamicform.web.form.Element element : elements){
            if(element instanceof Group && ( element.isShow() || isSubGroup )) {
                Element eGroup = doc.createElement(element.getName());
                rootElement.appendChild(eGroup);
                formatElement(element.getElements(),doc,eGroup,dataMap,false,0);
                if(element.isMultiple() && Integer.valueOf(dataMap.get(element.getName()+"#rows").toString())>1){
                    int rows = Integer.valueOf(dataMap.get(element.getName()+"#rows").toString());
                    for(int row = 1 ; row < rows ; row++){
                        Element rowGroup = doc.createElement(element.getName());
                        rootElement.appendChild(rowGroup);
                        formatElement(element.getElements(),doc,rowGroup,dataMap,false,row);                        
                    }
                }
            } else  if(element instanceof Field) {
                if(((Field) element).isContainsSubForm()){
                    List<com.poc.dynamicform.web.form.Element> subElements = new ArrayList<com.poc.dynamicform.web.form.Element>();
                    for(com.poc.dynamicform.web.form.Element el : element.getElements()){
                        if(((Group)el).getName().equals(dataMap.get(element.getName()))){
                            subElements.add(el);
                        }
                    }
                    formatElement(subElements,doc,rootElement,dataMap,true,0);
                }else{
                    Element  field = rootElement.getOwnerDocument().createElement(element.getName());
                    if(dataMap.get(element.getName()) != null){
                        if(rowsIndex==0){
                            field.appendChild(doc.createTextNode(dataMap.get(element.getName()).toString()));
                            rootElement.appendChild(field);
                        }else{
                            field.appendChild(doc.createTextNode(getRowValue(result,element.getName(),rowsIndex)));
                            rootElement.appendChild(field);
                        }
                    }
                }
            }
        }
    }

    
    /**
     * 
     * @param dataMap
     * @param name
     * @param rowsIndex
     * @return
     */
    private String getRowValue(Map<String,Object> dataMap, String name, Integer rowsIndex) {
        
        int index = 1;
        String regex = "^\\w*_[0-9]$";
        for (Entry<String, Object> entry : dataMap.entrySet()) {
            if(entry.getKey().startsWith(name) && entry.getKey().matches(regex)){
                if(rowsIndex==index){
                    return entry.getValue() != null ? entry.getValue().toString() : "";
                }
                index++;
            }
        }
        return "";
    }

}