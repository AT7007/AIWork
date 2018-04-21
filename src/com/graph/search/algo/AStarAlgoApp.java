package com.graph.search.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import java.util.List;

class AStarAlgoApp {

	public static void main(String[] args) {

		NodeElement node1 = new NodeElement("A");
		NodeElement node2 = new NodeElement("B");
		NodeElement node3 = new NodeElement("C");
		NodeElement node4 = new NodeElement("D");
		NodeElement node5 = new NodeElement("E");
		NodeElement node6 = new NodeElement("F");

		node1.addNeighbour(new Edge(node2, 4));
		node1.addNeighbour(new Edge(node3, 2));

		node2.addNeighbour(new Edge(node3, 5));
		node2.addNeighbour(new Edge(node4, 10));

		node3.addNeighbour(new Edge(node5, 3));

		node4.addNeighbour(new Edge(node6, 11));

		node5.addNeighbour(new Edge(node4, 4));

		Algorithm algorithm = new Algorithm();

		algorithm.aStarSearch(node1, node6);

		List<NodeElement> path = algorithm.printPath(node6);
		System.out.println("Path " + path);
	}

}

class Edge {

	public final double cost;
	public final NodeElement targetNode;

	public Edge(NodeElement targetNode, double cost) {
		this.targetNode = targetNode;
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

	public NodeElement getTargetNode() {
		return targetNode;
	}
}

class NodeElement implements Comparable<NodeElement> {

	private String value;
	private double gScore;
	private double fScore = 0;
	private double x;
	private double y;
	private List<Edge> adjacenciesList;
	private NodeElement parentNode;

	public NodeElement(String value) {
		this.value = value;
		this.adjacenciesList = new ArrayList<>();
	}

	public double getgScore() {
		return gScore;
	}

	public void addNeighbour(Edge edge) {
		this.adjacenciesList.add(edge);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setgScore(double gScore) {
		this.gScore = gScore;
	}

	public double getfScore() {
		return fScore;
	}

	public void setfScore(double fScore) {
		this.fScore = fScore;
	}

	public NodeElement getParentNode() {
		return parentNode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setParentNode(NodeElement parentNode) {
		this.parentNode = parentNode;
	}

	public List<Edge> getAdjacenciesList() {
		return adjacenciesList;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public int compareTo(NodeElement otherNode) {
		return Double.compare(this.fScore, otherNode.getfScore());
	}
}

class Algorithm {

	public void aStarSearch(NodeElement sourceNode, NodeElement goalNode) {

		Set<NodeElement> exploredNodes = new HashSet<NodeElement>();

		PriorityQueue<NodeElement> unexploredNodesQueue = new PriorityQueue<NodeElement>();
		sourceNode.setgScore(0);
		unexploredNodesQueue.add(sourceNode);
		boolean found = false;

		while (!unexploredNodesQueue.isEmpty() && !found) {

			NodeElement currentNode = unexploredNodesQueue.poll();
			exploredNodes.add(currentNode);

			if (currentNode.getValue().equals(goalNode.getValue())) {
				found = true;
			}

			for (Edge e : currentNode.getAdjacenciesList()) {
				NodeElement childNode = e.getTargetNode();
				double cost = e.getCost();
				double tempGScore = currentNode.getgScore() + cost;
				double tempFScore = tempGScore + heuristic(childNode, goalNode);

				if (exploredNodes.contains(childNode) && (tempFScore >= childNode.getfScore())) {
					continue;
				} else if (!unexploredNodesQueue.contains(childNode) || (tempFScore < childNode.getfScore())) {

					childNode.setParentNode(currentNode);
					childNode.setgScore(tempGScore);
					childNode.setfScore(tempFScore);

					if (unexploredNodesQueue.contains(childNode)) {
						unexploredNodesQueue.remove(childNode);
					}

					unexploredNodesQueue.add(childNode);
				}
			}
		}
	}

	public List<NodeElement> printPath(NodeElement targetNode) {

		List<NodeElement> pathList = new ArrayList<NodeElement>();

		for (NodeElement node = targetNode; node != null; node = node.getParentNode()) {
			pathList.add(node);
		}

		Collections.reverse(pathList);

		return pathList;
	}

	// Manhattan heuristic/distance !!!
	public double heuristic(NodeElement node1, NodeElement node2) {
		return Math.abs(node1.getX() - node2.getX()) + Math.abs(node2.getY() - node2.getY());
	}
}
