import Axios from 'axios';

// server url
const url = 'http://localhost:8080'

export const getBusinessTermsRequest = () =>
    Axios.get(`${url}/businessterms`, {});

export const getDatabaseListRequest = () =>
    Axios.get(`${url}/getDomains`);

export const getNodesRequest = () =>
    Axios.get(`${url}/getAllNodes`);

export const getPropertiesBasedOnDBRequest = DBName =>
    Axios.get(`${url}/properties/dbName/${DBName}`)

export const postPropertyToBusinessTerm = params => 
    Axios.post(`${url}/businessterms`, params)

// export const getRelationshipsRequest = nodeId =>
//     Axios.get(`${url}/relationships?node=${nodeId}`);

// export const getPropertiesRequest = nodeId =>
//     Axios.get(`${url}/properties?node=${nodeId}`);
