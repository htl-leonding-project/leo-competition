package at.htl.leocompetition.oldentities;

import leoturniercore.DBConnection;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "NODE")
@NamedQueries({@NamedQuery(name = "Node.findById", query = "SELECT n FROM Node n WHERE n.id = :id"), @NamedQuery(name = "Node.findByPhase", query = "SELECT n FROM Node n WHERE n.phase = :phase")})
public class Node implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "LEVEL")
    private Integer levelintree;
    @JoinColumn(name = "PHASE_PHASENID", referencedColumnName = "PHASENID")
    @ManyToOne
    private model.Phase phase;
    @JoinColumn(name = "SPIEL_SPIELID", referencedColumnName = "SPIELID")
    @OneToOne(cascade=CascadeType.ALL)
    private model.Spiel spiel;
    @OneToMany(mappedBy = "parentNode")
    private Collection<Node> nodeCollection;
    @JoinColumn(name = "PARENTNODE", referencedColumnName = "ID")
    @ManyToOne
    private Node parentNode;
    @OneToMany(mappedBy = "rightNode")
    private Collection<Node> nodeCollection1;
    @JoinColumn(name = "RIGHTNODE", referencedColumnName = "ID")
    @ManyToOne
    private Node rightNode;
    @OneToMany(mappedBy = "centerNode")
    private Collection<Node> nodeCollection2;
    @JoinColumn(name = "CENTERNODE", referencedColumnName = "ID")
    @ManyToOne
    private Node centerNode;
    @OneToMany(mappedBy = "leftNode")
    private Collection<Node> nodeCollection3;
    @JoinColumn(name = "LEFTNODE", referencedColumnName = "ID")
    @ManyToOne
    private Node leftNode;

    public Node() {
    }

    public Node(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevelintree() {
        return levelintree;
    }

    public void setLevelintree(Integer level) {
        this.levelintree = level;
    }

    public Collection<Node> getNodeCollection() {
        return nodeCollection;
    }

    public void setNodeCollection(Collection<Node> nodeCollection) {
        this.nodeCollection = nodeCollection;
    }

    public Node getParent() {
        return parentNode;
    }

    public void setParent(Node parent) {
        this.parentNode = parent;
    }

    public Collection<Node> getNodeCollection1() {
        return nodeCollection1;
    }

    public void setNodeCollection1(Collection<Node> nodeCollection1) {
        this.nodeCollection1 = nodeCollection1;
    }

    public Node getRight() {
        return rightNode;
    }

    public void setRight(Node right) {
        this.rightNode = right;
    }

    public Collection<Node> getNodeCollection2() {
        return nodeCollection2;
    }

    public void setNodeCollection2(Collection<Node> nodeCollection2) {
        this.nodeCollection2 = nodeCollection2;
    }

    public Node getCenter() {
        return centerNode;
    }

    public void setCenter(Node center) {
        this.centerNode = center;
    }

    public Collection<Node> getNodeCollection3() {
        return nodeCollection3;
    }

    public void setNodeCollection3(Collection<Node> nodeCollection3) {
        this.nodeCollection3 = nodeCollection3;
    }

    public Node getLeft() {
        return leftNode;
    }

    public void setLeft(Node left) {
        this.leftNode = left;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Node)) {
            return false;
        }
        Node other = (Node) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Node[id=" + id + "]";
    }

    public void show() {
//        System.out.println("******************************************");
//        //System.out.println("id = " + getId() + "   dataNode = " + getData());
//        System.out.println("id = " + getId());
//        System.out.println("******************************************");

    }

    public model.Phase getPhase() {
        return phase;
    }

    public void setPhase(model.Phase phase) {
        this.phase = phase;
    }

    public model.Spiel getSpiel() {
        return spiel;
    }

    public void setSpiel(model.Spiel spiel) {
        this.spiel = spiel;
    }
    
    public void persist()
    {
        DBConnection.getEntityTransaction().begin();
        DBConnection.getEntityManager().persist(this);
        DBConnection.getEntityTransaction().commit();
    }
    
    public void delete()
    {
        DBConnection.deleteNode(this);
    }
}
