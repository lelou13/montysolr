package org.apache.lucene.queryparser.flexible.aqp.processors;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.flexible.messages.MessageImpl;
import org.apache.lucene.queryparser.flexible.core.QueryNodeException;
import org.apache.lucene.queryparser.flexible.core.QueryNodeParseException;
import org.apache.lucene.queryparser.flexible.core.config.QueryConfigHandler;
import org.apache.lucene.queryparser.flexible.core.messages.QueryParserMessages;
import org.apache.lucene.queryparser.flexible.core.nodes.ModifierQueryNode;
import org.apache.lucene.queryparser.flexible.core.nodes.ModifierQueryNode.Modifier;
import org.apache.lucene.queryparser.flexible.core.nodes.QueryNode;
import org.apache.lucene.queryparser.flexible.core.processors.QueryNodeProcessor;
import org.apache.lucene.queryparser.flexible.core.processors.QueryNodeProcessorImpl;
import org.apache.lucene.queryparser.flexible.aqp.config.AqpInvenioQueryConfigHandler;
import org.apache.lucene.queryparser.flexible.aqp.config.InvenioQueryAttribute;
import org.apache.lucene.queryparser.flexible.aqp.config.InvenioQueryAttribute.Channel;
import org.apache.lucene.queryparser.flexible.aqp.config.InvenioQueryAttribute.QMode;
import org.apache.lucene.queryparser.flexible.aqp.nodes.AqpANTLRNode;
import org.apache.lucene.queryparser.flexible.aqp.nodes.InvenioQueryNode;
import org.apache.lucene.search.InvenioQuery;

/**
 * Creates a {@link ModifierQueryNode} from the MODIFIER node 
 * last child
 *  
 * If MODIFIER node contains only one child, we return that child and do 
 * nothing.
 * <br/>
 * 
 * If BOOST node contains two children, we take the first and check its
 * input, eg.
 * <pre>
 *               MODIFIER
 *                  /  \
 *                 #  rest
 * </pre>
 * 
 * We create an {@link InvenioQuery} node for nodes that have the modifier
 * with value '#'
 * <br/>
 * 
 * This processor should run late enough to be sure that the query was already 
 * translated.
 * 
 * @see Modifier
 * @see AqpBooleanQueryNode
 */
public class AqpInvenioMODIFIERProcessor extends QueryNodeProcessorImpl implements
		QueryNodeProcessor {
	
	private Channel channel = null;
	private QMode qMode = null;
	private ArrayList<String> xFields = null;

	@Override
	protected QueryNode preProcessNode(QueryNode node)
			throws QueryNodeException {
		if (node instanceof AqpANTLRNode && ((AqpANTLRNode) node).getTokenLabel().equals("MODIFIER")) {
			
			if (node.getChildren().size()==1) {
				node = node.getChildren().get(0); // usually TMODIFIER
				ArrayList<String> xFields = getExcludedFields();
				String field = getExistingField(node, 2); //how deep to search for FIELD
				InvenioQueryNode iq;
				
				if (getDefaultQMode() == QMode.MAXINV) {
					if (xFields == null || !xFields.contains(field)) {
						iq = new InvenioQueryNode(node, getIdField());
						iq.setChannel(getDefaultChannel());
						iq.setSearchField(field);
						return iq;
					}
				}
				else if (getDefaultQMode() == qMode.MAXSOLR) {
					if (xFields.contains(field)) {
						iq = new InvenioQueryNode(node, getIdField());
						iq.setChannel(getDefaultChannel());
						iq.setSearchField(field);
						return iq;
					}
				}
				return node;
			}
			
			
			String modifier = ((AqpANTLRNode) node.getChildren().get(0)).getTokenName();
			node = node.getChildren().get(node.getChildren().size()-1);
			if (modifier.equals("BAR")) {
				String field = getExistingField(node, 2); //how deep to search for FIELD
				InvenioQueryNode iq = new InvenioQueryNode(node, getIdField());
				iq.setChannel(getDefaultChannel());
				iq.setSearchField(field);
				return iq;
			}
			else {
				throw new QueryNodeParseException(new MessageImpl("Unknown modifier: " + modifier));
			}
			
		}
		return node;
		
		
	}

	@Override
	protected QueryNode postProcessNode(QueryNode node)
			throws QueryNodeException {
		return node;
	}

	@Override
	protected List<QueryNode> setChildrenOrder(List<QueryNode> children)
			throws QueryNodeException {
		return children;
	}
	
	private String getIdField() throws QueryNodeException {
		QueryConfigHandler queryConfig = getQueryConfigHandler();
		if (queryConfig == null || !queryConfig.has(AqpInvenioQueryConfigHandler.ConfigurationKeys.INVENIO_DEFAULT_ID_FIELD)) {
			throw new QueryNodeException(new MessageImpl(
	                QueryParserMessages.LUCENE_QUERY_CONVERSION_ERROR,
	                "Configuration error: INVENIO_DEFAULT_ID_FIELD is missing"));
		}
		return queryConfig.get(
				AqpInvenioQueryConfigHandler.ConfigurationKeys.INVENIO_DEFAULT_ID_FIELD);
	}
	
	
	private Channel getDefaultChannel() throws QueryNodeException {
		if (channel != null) {
			return channel;
		}
		
		channel =  getInvenioQueryAttribute().getChannel();
		return channel;
	}
	
	private QMode getDefaultQMode() throws QueryNodeException {
		if (qMode != null) {
			return qMode;
		}
		
		qMode =  getInvenioQueryAttribute().getMode();
		return qMode;
	}
	
	private ArrayList<String> getExcludedFields() throws QueryNodeException {
		if (xFields != null) {
			return xFields;
		}
		
		xFields =  (ArrayList<String>) getInvenioQueryAttribute().getOverridenFields();
		return xFields;
	}
	
	
	private String getExistingField(QueryNode curNode, int level) {
		if (level < 1 || curNode == null || curNode.getChildren() == null) {
			return null;
		}
		
		for (QueryNode child: curNode.getChildren()) {
			if (child instanceof AqpANTLRNode && ((AqpANTLRNode) child).getTokenLabel().equals("FIELD")) {
				if (child.getChildren()!=null) {
					return ((AqpANTLRNode) child.getChildren().get(0)).getTokenInput();
				}
			}
		}
		return getExistingField(curNode.getChildren().get(curNode.getChildren().size()-1), level-1);
	}
	
	private InvenioQueryAttribute getInvenioQueryAttribute() throws QueryNodeException {
		QueryConfigHandler queryConfig = getQueryConfigHandler();
		if (queryConfig == null || !queryConfig.has(AqpInvenioQueryConfigHandler.ConfigurationKeys.INVENIO_QUERY)) {
			throw new QueryNodeException(new MessageImpl(
	                QueryParserMessages.LUCENE_QUERY_CONVERSION_ERROR,
	                "Configuration error: INVENIO_QUERY is missing"));
		}
		return queryConfig.get(AqpInvenioQueryConfigHandler.ConfigurationKeys.INVENIO_QUERY);
	}

}
