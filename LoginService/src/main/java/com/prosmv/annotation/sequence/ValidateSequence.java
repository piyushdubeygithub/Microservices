package com.prosmv.annotation.sequence;

import javax.validation.GroupSequence;

import com.prosmv.annotation.groups.ConfirmPasswordGroup;
import com.prosmv.annotation.groups.NotNullGroup;

@GroupSequence(value = { NotNullGroup.class ,ConfirmPasswordGroup.class})
public interface ValidateSequence {

}
