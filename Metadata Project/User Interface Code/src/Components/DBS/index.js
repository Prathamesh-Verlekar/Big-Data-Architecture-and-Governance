import React, { useState, useEffect } from 'react';
import './index.css';
import { Select, Button, Modal, message, Table, Space, Tooltip, Divider} from 'antd';
import { getDatabaseListRequest, getBusinessTermsRequest, getNodesRequest, getPropertiesBasedOnDBRequest, postPropertyToBusinessTerm } from './api';
const { Option } = Select;

function DBS() {

    const [dbName, setDbName] = useState('');
    const [domainName, setDomainName] = useState('');
    const [databaseList, setDatabaseList] = useState([]);
    const [businessTerms, setBusinessTerms] = useState([]);
    const [nodesInformation, setNodesInformation] = useState([]);
    const [isModalVisible, setIsModalVisible] = useState(false);
    const [isNodeModalVisible, setIsNodeModalVisible] = useState(false);
    const [isRelModalVisible, setIsRelModalVisible] = useState(false);
    const [isProModalVisible, setIsProModalVisible] = useState(false);
    const [NodeModalContent, setNodeModalContent] = useState([]);
    const [RelModalContent, setRelModalContent] = useState([]);
    const [ProModalContent, setProModalContent] = useState([]);
    const [domainChosen, setDomainChosen] = useState('');
    const [businessTermChosen, setBusinessTermChosen] = useState('');
    const [parameterChosen, setParameterChosen] = useState('');
    const [propertyChoices, setPropertyChoices] = useState([]);
    const [isLoading, setIsLoading] = useState(false);

    // handle the modal for showing business terms
    const showModal = () => {
        setIsModalVisible(true);
    };

    const handleOk = () => {
        setIsModalVisible(false);
    };

    const handleCancel = () => {
        setIsModalVisible(false);
    };

    // handle the modal for showing node
    const showNodeModal = () => {
        setIsNodeModalVisible(true);
    };

    const handleNodeOk = () => {
        setIsNodeModalVisible(false);
    };

    const handleNodeCancel = () => {
        setIsNodeModalVisible(false);
    };

    // handle the modal for showing relationship
    const showRelModal = () => {
        setIsRelModalVisible(true);
    };

    const handleRelOk = () => {
        setIsRelModalVisible(false);
    };

    const handleRelCancel = () => {
        setIsRelModalVisible(false);
    };

    // handle the modal for showing property
    const showProModal = () => {
        setIsProModalVisible(true);
    };

    const handleProOk = () => {
        setIsProModalVisible(false);
    };

    const handleProCancel = () => {
        setIsProModalVisible(false);
    };

    // change value while value changed
    const onChange = value => {
        const e = JSON.parse(value);
        setDbName(e.DBName);
        setDomainName(e.DomainName);
    }

    // change value while value changed
    const onChangeOfDomain = value => {
        // const e = JSON.parse(value);
        setDomainChosen(value);
        setPropertyChoices([]);
        getPropertiesBasedOnDBRequest(JSON.parse(value).DBName).then(response => {
            setParameterChosen('');
            console.log("********properties list********");
            console.log(response.data);
            console.log("********properties list********");
            let propertyChoices = [];
            response.data.forEach((e, i) => {
                propertyChoices.push({
                    key: `${i}`,
                    techTerm: e.techTerm,
                    property: e,
                });
            });
            setPropertyChoices(propertyChoices);
        }).catch(err => {
            message.error('Network is unstable.')
        })
    }

    // change value while value changed
    const onChangeOfBusinessTerm = value => {
        // const e = JSON.parse(value);
        setBusinessTermChosen(value);
    }

    // change value while value changed
    const onChangeOfParameter = value => {
        // const e = JSON.parse(value);
        setParameterChosen(value);
    }

    // get database list info
    const getDatabaseList = () => {
        getDatabaseListRequest().then(response => {
            console.log("********database list********");
            console.log(response.data);
            console.log("********database list********");
            let databaseList = [];
            response.data.forEach((e, i) => {
                databaseList.push({
                    key: `${i}`,
                    DBName: e.dbName,
                    DomainName: e.domainName,
                })
            });
            setDatabaseList(databaseList);
        }).catch(err => {
            message.error('Network is unstable.')
        })
    }

    // search all of the business terms
    const getInformation = () => {
        // setBusinessTerms(null);
        // showModal();
        // get business metadata from database
        getBusinessTermsRequest().then(response => {
            console.log("********business terms information********");
            console.log(response.data);
            console.log("********business terms information********");
            let businessTerms = [];
            response.data.forEach((e, i) => {
                businessTerms.push({
                    key: `${i}`,
                    businessId: e.businessId,
                    businessDesc: e.businessDesc,
                    businessType: e.busType,
                    propertyList: e.propertyList,
                    businessTerm: e,
                });
            });
            setBusinessTerms(businessTerms);
        }).catch(err => {
            message.error('Network is unstable.')
        })
    }

    // get all nodes information
    const getNodes = () => {
        getNodesRequest().then(response => {
            console.log("********Nodes List********");
            console.log(response.data);
            console.log("********Nodes List********");
            let nodesInformation = [];
            response.data.forEach((e, i) => {
                nodesInformation.push({
                    key: `${i}`,
                    label: e.label,
                    counts: e.counts,
                    DBName: e.dbName,
                    propertyList: e.props,
                    relationshipList: e.relList,
                })
            });
            setNodesInformation(nodesInformation);
        }).catch(err => {
            message.error('Network is unstable.')
        })
    }

    // show property of chosen business term
    const showProperty = propertyList => {
        let ProModalContent = [];
        propertyList.forEach((e, i) => {
            ProModalContent.push({
                key: `${i}`,
                techTerm: e.techTerm,
                uniqCons: e.uniqueConstraints,
                existCons: e.existingConstraints,
            })
        });
        setProModalContent(ProModalContent);
        showProModal();
    }

    // show relationships of chosen business term
    const showRelationships = relationshipList => {
        let RelModalContent = [];
        relationshipList.forEach((e, i) => {
            RelModalContent.push({
                key: `${i}`,
                relId: e.relationshipId,
                relDesc: e.relDesc,
            })
        });
        setRelModalContent(RelModalContent);
        showRelModal();
    }

    // search all nodes about the chosen database
    const searchNodeInformation = () => {
        if (domainName && domainName !== '') {
            setNodeModalContent(null);
            showNodeModal();
            // filer nodes information in all nodes
            setNodeModalContent(nodesInformation.filter(e => e.DBName === dbName));
        } else {
            message.error('Value is empty!');
        }
    }

    // Add property to business term
    const addPropToBus = () => {
        if (domainChosen === '' || businessTermChosen === '' || parameterChosen === '') {
            message.error('Value is empty!');
        } else {
            let newparams = JSON.parse(businessTermChosen).businessTerm;
            const newProperty = JSON.parse(parameterChosen).property;
            // whether there is such a property in this business term
            let hasOne = false;
            for (var prop of newparams.propertyList) {
                if (prop.propertyId === newProperty.propertyId) {
                    hasOne = true;
                    break;
                }
            }
            if (!hasOne) {
                setIsLoading(true);
                newparams.propertyList.push(newProperty);
                console.log(newparams);
                postPropertyToBusinessTerm(newparams).then(response => {
                    console.log(response);
                    // Update
                    window.location.reload();
                }).catch(err => {
                    message.error('Something wrong');
                    setIsLoading(false);
                })
            } else {
                message.error('There is already a property in this business term.')
            }
        }
    }

    // Delete property from business term
    const deletePropFromBus = () => {
        if (domainChosen === '' || businessTermChosen === '' || parameterChosen === '') {
            message.error('Value is empty!');
        } else {
            let newparams = JSON.parse(businessTermChosen).businessTerm;
            const newProperty = JSON.parse(parameterChosen).property;
            // whether there is such a property in this business term
            let hasOne = false;
            for (var i = 0; i < newparams.propertyList.length; i ++) {
                if (newparams.propertyList[i].propertyId === newProperty.propertyId) {
                    hasOne = true;
                    newparams.propertyList.splice(i, 1);
                    break;
                }
            }
            if (hasOne) {
                setIsLoading(true);
                console.log(newparams);
                postPropertyToBusinessTerm(newparams).then(response => {
                    console.log(response);
                    // Update
                    window.location.reload();
                }).catch(err => {
                    message.error('Something wrong');
                    setIsLoading(false);
                })
            } else {
                message.error('There is no such a property in this business term.')
            }
        }
    }

    // Init => Get database list
    useEffect(() => {
        getDatabaseList();
        getInformation();
        getNodes();
    }, []);

    // Column for business terms
    const businessTermsColumn = [
        {
            title: 'Business Description',
            dataIndex: 'businessDesc',
            key: 'businessDesc',
            render: (text, record) => (
                <Tooltip title={record.businessDesc}>
                    <span className="ellipsis">{record.businessDesc}</span>
                </Tooltip>
            ),
        },
        {
            title: 'Business Type',
            dataIndex: 'businessType',
            key: 'businessType',
        },
        {
            title: 'Action',
            key: 'action',
            render: (text, record) => (
                <Space size="middle">
                    <Button type='primary' shape='round' onClick={() => showProperty(record.propertyList)}>
                        Properties
                    </Button>
                </Space>
            ),
        },
    ];

    // Columns for nodes
    const nodeModalColumn = [
        {
            title: 'Label',
            dataIndex: 'label',
            key: 'label',
        },
        {
            title: 'Counts',
            dataIndex: 'counts',
            key: 'counts',
        },
        {
            title: 'Action',
            key: 'action',
            width: 250,
            render: (text, record) => (
                <Space size="middle">
                    <Button type='primary' shape='round' onClick={() => showRelationships(record.relationshipList)}>
                        Relationships
                    </Button>
                    <Button type='primary' shape='round' onClick={() => showProperty(record.propertyList)}>
                        Properties
                    </Button>
                </Space>
            ),
        },
    ];

    // Columns for relationship
    const relModalColumn = [
        {
            title: 'Relationship Id',
            dataIndex: 'relId',
            key: 'relId',
        },
        {
            title: 'Relationship Description',
            dataIndex: 'relDesc',
            key: 'relDesc',
        },
    ];

    // Columns for property
    const proModalColumn = [
        {
            title: 'Technical Term',
            dataIndex: 'techTerm',
            key: 'techTerm',
        },
        {
            title: 'Unique Constraint',
            dataIndex: 'uniqCons',
            key: 'uniqCons',
        },
        {
            title: 'Existing Constraint',
            dataIndex: 'existCons',
            key: 'existCons',
        },
    ];

    return (
        <div className="container">
            <Divider style={{marginBottom: 40, fontSize: 14, color: 'gray'}}>Browse All Metadata</Divider>
            <Button type="primary" shape="round" style={{ width: 350 }} onClick={showModal}>
                Browse Metadata
            </Button>
            <Divider style={{marginBottom: 40, marginTop: 40, fontSize: 14, color: 'gray'}}>Browse Nodes According To DBname</Divider>
            <Select
                showSearch
                style={{ width: 200 }}
                placeholder="Choose DB"
                optionFilterProp="children"
                onChange={onChange}
                filterOption={(input, option) =>
                    option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                }
            >
                {databaseList.map((element, index) =>
                    <Option key={index} value={JSON.stringify(element)}>{element.DBName}</Option>
                )}
            </Select>
            <Button type="primary" shape="round" style={{ marginLeft: '15px' }} onClick={searchNodeInformation}>
                Browse Nodes
            </Button>
            <Divider style={{marginBottom: 40, marginTop: 40, fontSize: 14, color: 'gray'}}>Operations on Business Terms</Divider>
            <Select
                showSearch
                style={{ width: 200 }}
                placeholder="Domain"
                optionFilterProp="children"
                onChange={onChangeOfDomain}
                filterOption={(input, option) =>
                    option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                }
            >
                {databaseList.map((element, index) =>
                    <Option key={index} value={JSON.stringify(element)}>{element.DomainName}</Option>
                )}
            </Select>
            <Select
                showSearch
                style={{ width: 200, marginLeft: 20 }}
                placeholder="Database"
                optionFilterProp="children"
                value={domainChosen === '' ? null : domainChosen}
                filterOption={(input, option) =>
                    option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                }
                disabled
            >
                {databaseList.map((element, index) =>
                    <Option key={index} value={JSON.stringify(element)}>{element.DBName}</Option>
                )}
            </Select>
            <br />
            <br />
            <Select
                showSearch
                style={{ width: 200 }}
                placeholder="Business Term"
                optionFilterProp="children"
                onChange={onChangeOfBusinessTerm}
                filterOption={(input, option) =>
                    option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                }
            >
                {businessTerms.map((element, index) =>
                    <Option key={index} value={JSON.stringify(element)}>{element.businessDesc}</Option>
                )}
            </Select>
            <Select
                showSearch
                style={{ width: 200, marginLeft: 20 }}
                placeholder="Parameter"
                optionFilterProp="children"
                onChange={onChangeOfParameter}
                value={parameterChosen === '' ? null : parameterChosen}
                filterOption={(input, option) =>
                    option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                }
            >
                {propertyChoices.map((element, index) =>
                    <Option key={index} value={JSON.stringify(element)}>{element.techTerm}</Option>
                )}
            </Select>
            <br />
            <br />
            <Button type="primary" shape="round" onClick={deletePropFromBus} loading={isLoading}>
                DELETE
            </Button>
            <Button type="primary" shape="round" style={{ marginLeft: 20 }} onClick={addPropToBus} loading={isLoading}>
                ADD
            </Button>

            {/* modal for showing Business Terms */}
            <Modal title="Business Terms" visible={isModalVisible} onOk={handleOk} onCancel={handleCancel} maskClosable={false} width={600} zIndex="2">
                <Table columns={businessTermsColumn} dataSource={businessTerms} pagination={{ pageSize: 5 }} />
            </Modal>

            {/* modal for showing nodes */}
            <Modal title={dbName} visible={isNodeModalVisible} onOk={handleNodeOk} onCancel={handleNodeCancel} maskClosable={false} width={600} zIndex="2">
                <Table columns={nodeModalColumn} dataSource={NodeModalContent} pagination={{ pageSize: 5 }} />
            </Modal>

            {/* modal for showing relationship */}
            <Modal visible={isRelModalVisible} onOk={handleRelOk} onCancel={handleRelCancel} maskClosable={false} width={600} zIndex="4">
                <Table columns={relModalColumn} dataSource={RelModalContent} pagination={{ pageSize: 5 }} />
            </Modal>

            {/* modal for showing property */}
            <Modal visible={isProModalVisible} onOk={handleProOk} onCancel={handleProCancel} maskClosable={false} width={600} zIndex="4">
                <Table columns={proModalColumn} dataSource={ProModalContent} pagination={{ pageSize: 5 }} />
            </Modal>
        </div>
    );
}

export default DBS;
