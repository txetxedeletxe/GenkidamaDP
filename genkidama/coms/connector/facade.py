from .client import GenkidamaIPClient

def ip_connect(hostname=None, port=None, connection_args=dict()):
    ip_client = GenkidamaIPClient(
                    hostname=hostname,
                    port=port,
                    connection_args=connection_args)
    return ip_client.connect()

def connect(hint=None, type_="auto", connection_args=dict()): # TODO improve this
    # Check hint and guess type
    if type_ == "auto" or type_ == "ip": 
        hostname = None
        port = None
            
        if isinstance(hint,list) or isinstance(hint,tuple): # Hostname and port provided
            if len(hint) == 2:
                hostname, port = hint
                
                if ((hostname is None or isinstance(hostname,str)) and 
                        port is None or isinstance(port,int)):
                    type_ = "ip"
                
        if isinstance(hint,str): # Only hostname provided
            type_ = "ip"
            hostname = hint
            port = None
        
            
    
    if type_ == "ip":
        if (hint is not None and 
            (hostname is None and port is None)):
            raise ValueError("Hint not compatible with connection type \"ip\".")
                             
        return ip_connect(
                hostname=hostname,
                port=port,
                connection_args=connection_args)
            
    if type_ == "auto":
        raise ValueError("Could not guess connection type from hint.")
                         
    
    