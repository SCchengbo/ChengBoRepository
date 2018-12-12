# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: MsgLogicWather.proto

from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import descriptor_pb2
# @@protoc_insertion_point(imports)


import MsgCode_pb2
import MsgGate_pb2
import MsgLogic_pb2


DESCRIPTOR = _descriptor.FileDescriptor(
  name='MsgLogicWather.proto',
  package='ProtoNet',
  serialized_pb='\n\x14MsgLogicWather.proto\x12\x08ProtoNet\x1a\rMsgCode.proto\x1a\rMsgGate.proto\x1a\x0eMsgLogic.proto\"\r\n\x0bReqSeatDown\"7\n\x0bResSeatDown\x12(\n\x06result\x18\x01 \x02(\x0b\x32\x18.ProtoNet.ResponseResult\"\r\n\x0bReqBeWather\"7\n\x0bResBeWather\x12(\n\x06result\x18\x01 \x02(\x0b\x32\x18.ProtoNet.ResponseResult\"D\n\x12ResNotifyAddWather\x12.\n\rsdkController\x18\x01 \x02(\x0b\x32\x17.ProtoNet.SdkController\"G\n\x15ResNotifyRemoveWather\x12.\n\rsdkController\x18\x01 \x02(\x0b\x32\x17.ProtoNet.SdkControllerB\x19\n\x17\x63om.version.protobuf.pb')




_REQSEATDOWN = _descriptor.Descriptor(
  name='ReqSeatDown',
  full_name='ProtoNet.ReqSeatDown',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=80,
  serialized_end=93,
)


_RESSEATDOWN = _descriptor.Descriptor(
  name='ResSeatDown',
  full_name='ProtoNet.ResSeatDown',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='result', full_name='ProtoNet.ResSeatDown.result', index=0,
      number=1, type=11, cpp_type=10, label=2,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=95,
  serialized_end=150,
)


_REQBEWATHER = _descriptor.Descriptor(
  name='ReqBeWather',
  full_name='ProtoNet.ReqBeWather',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=152,
  serialized_end=165,
)


_RESBEWATHER = _descriptor.Descriptor(
  name='ResBeWather',
  full_name='ProtoNet.ResBeWather',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='result', full_name='ProtoNet.ResBeWather.result', index=0,
      number=1, type=11, cpp_type=10, label=2,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=167,
  serialized_end=222,
)


_RESNOTIFYADDWATHER = _descriptor.Descriptor(
  name='ResNotifyAddWather',
  full_name='ProtoNet.ResNotifyAddWather',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='sdkController', full_name='ProtoNet.ResNotifyAddWather.sdkController', index=0,
      number=1, type=11, cpp_type=10, label=2,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=224,
  serialized_end=292,
)


_RESNOTIFYREMOVEWATHER = _descriptor.Descriptor(
  name='ResNotifyRemoveWather',
  full_name='ProtoNet.ResNotifyRemoveWather',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='sdkController', full_name='ProtoNet.ResNotifyRemoveWather.sdkController', index=0,
      number=1, type=11, cpp_type=10, label=2,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=294,
  serialized_end=365,
)

_RESSEATDOWN.fields_by_name['result'].message_type = MsgCode_pb2._RESPONSERESULT
_RESBEWATHER.fields_by_name['result'].message_type = MsgCode_pb2._RESPONSERESULT
_RESNOTIFYADDWATHER.fields_by_name['sdkController'].message_type = MsgLogic_pb2._SDKCONTROLLER
_RESNOTIFYREMOVEWATHER.fields_by_name['sdkController'].message_type = MsgLogic_pb2._SDKCONTROLLER
DESCRIPTOR.message_types_by_name['ReqSeatDown'] = _REQSEATDOWN
DESCRIPTOR.message_types_by_name['ResSeatDown'] = _RESSEATDOWN
DESCRIPTOR.message_types_by_name['ReqBeWather'] = _REQBEWATHER
DESCRIPTOR.message_types_by_name['ResBeWather'] = _RESBEWATHER
DESCRIPTOR.message_types_by_name['ResNotifyAddWather'] = _RESNOTIFYADDWATHER
DESCRIPTOR.message_types_by_name['ResNotifyRemoveWather'] = _RESNOTIFYREMOVEWATHER

class ReqSeatDown(_message.Message):
  __metaclass__ = _reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _REQSEATDOWN

  # @@protoc_insertion_point(class_scope:ProtoNet.ReqSeatDown)

class ResSeatDown(_message.Message):
  __metaclass__ = _reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _RESSEATDOWN

  # @@protoc_insertion_point(class_scope:ProtoNet.ResSeatDown)

class ReqBeWather(_message.Message):
  __metaclass__ = _reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _REQBEWATHER

  # @@protoc_insertion_point(class_scope:ProtoNet.ReqBeWather)

class ResBeWather(_message.Message):
  __metaclass__ = _reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _RESBEWATHER

  # @@protoc_insertion_point(class_scope:ProtoNet.ResBeWather)

class ResNotifyAddWather(_message.Message):
  __metaclass__ = _reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _RESNOTIFYADDWATHER

  # @@protoc_insertion_point(class_scope:ProtoNet.ResNotifyAddWather)

class ResNotifyRemoveWather(_message.Message):
  __metaclass__ = _reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _RESNOTIFYREMOVEWATHER

  # @@protoc_insertion_point(class_scope:ProtoNet.ResNotifyRemoveWather)


DESCRIPTOR.has_options = True
DESCRIPTOR._options = _descriptor._ParseOptions(descriptor_pb2.FileOptions(), '\n\027com.version.protobuf.pb')
# @@protoc_insertion_point(module_scope)
