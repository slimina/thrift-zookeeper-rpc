namespace java cn.slimsmart.thrift.rpc.demo
service EchoSerivce
{
	string echo(1: string msg);
}