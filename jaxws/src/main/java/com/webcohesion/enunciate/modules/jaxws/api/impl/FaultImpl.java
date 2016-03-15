package com.webcohesion.enunciate.modules.jaxws.api.impl;

import com.webcohesion.enunciate.api.datatype.BaseType;
import com.webcohesion.enunciate.api.datatype.DataType;
import com.webcohesion.enunciate.api.datatype.DataTypeReference;
import com.webcohesion.enunciate.api.services.Fault;
import com.webcohesion.enunciate.javac.javadoc.JavaDoc;
import com.webcohesion.enunciate.metadata.Label;
import com.webcohesion.enunciate.modules.jaxws.model.WebFault;

import javax.lang.model.element.AnnotationMirror;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Ryan Heaton
 */
public class FaultImpl implements Fault, DataTypeReference {

  private final WebFault fault;

  public FaultImpl(WebFault fault) {
    this.fault = fault;
  }

  @Override
  public String getName() {
    return this.fault.getMessageName();
  }

  @Override
  public String getConditions() {
    return this.fault.getConditions();
  }

  @Override
  public DataTypeReference getDataType() {
    return this;
  }

  @Override
  public BaseType getBaseType() {
    return BaseType.object;
  }

  @Override
  public String getLabel() {
    String value = getName();

    Label label = this.fault.getAnnotation(Label.class);
    if (label != null) {
      value = label.value();
    }

    JavaDoc.JavaDocTagList tags = this.fault.getJavaDoc().get("label");
    if (tags != null && tags.size() > 0) {
      String tag = tags.get(0).trim();
      value = tag.isEmpty() ? value : tag;
    }

    return value;
  }

  @Override
  public String getSlug() {
    //todo: faults as data types
    return null;
  }

  @Override
  public List<ContainerType> getContainers() {
    return Collections.emptyList();
  }

  @Override
  public DataType getValue() {
    //todo: faults as data types
    return null;
  }

  @Override
  public Map<String, AnnotationMirror> getAnnotations() {
    return this.fault.getAnnotations();
  }
}
