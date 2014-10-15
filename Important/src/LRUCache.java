import java.util.HashMap;
import java.util.LinkedList;

public class LRUCache {
    int cap ;
    int init;
    HashMap<Integer,DNode<Integer>> map;
    DLinkedList<Integer> q; 
	public LRUCache(int capacity) {
        map = new HashMap();
        q = new DLinkedList<Integer>();
        cap = capacity;
        init = 0;
	}
	public int get(int key) {
        if(!map.containsKey(key))
		    return -1;
		q.remove(map.get(key));
		q.add(map.get(key));
		return map.get(key).value;
	}

	public void set(int key, int value) {
        if(map.containsKey(key)){
            q.remove(map.get(key));
        	map.remove(key);
        }else{
            if(init>=cap)
                map.remove(q.poll().key);
            else
                init++;
        }
        DNode<Integer> w = new DNode(key , value);
        map.put(key,w);
        q.add(w);
        
	}
	public class DLinkedList<K>{
	    DNode<K> head;
	    DNode<K> tail;
        public DLinkedList(){};
        public void print() {
			// TODO Auto-generated method stub
			DNode<K> a = head;
			while(a!=null){
				System.out.print(a.key + " ");
				a=a.next;
			}
			System.out.println();
		}
		public void remove(DNode<K> u ){
			if(tail==u){
				if(tail==head){
					head= head.prev;
				}
            	tail = tail.prev;
            }
            if(u.prev!=null){
                u.prev.next = u.next;
                if(u.next!=null)
                    u.next.prev = u.prev;
            } else {
                if(head==tail)
                    tail=null;
                head=head.next;
                
                if(head!=null)head.prev = null;
            }
        }
        public void add(DNode<K> u ){
            if(tail==null){
                head = tail = u;
                u.next = null;
                u.prev = null;
            }else{
                u.prev = null;
                u.next = head;
                head.prev = u;
                head = u;
            }
//            print();
        }
        public DNode<K> poll(){
            if(tail==null)return null;
            if(tail==head){
                DNode <K> w = head;
                 head = tail = null;
                return w;
            }
            DNode <K> w = tail;
            tail = tail.prev;
            return w;
        }
	}
	public class DNode<K>{
	    DNode<K> prev;
	    DNode<K> next;
	    K key;
	    K value;
	    public DNode(K k ,K val){
	        value = val;
	        key = k;
	    }
	}
}