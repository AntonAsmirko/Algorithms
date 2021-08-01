#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <vector>
#include <iostream>
#include <list>
#include <utility>
#include <cmath>
#include <algorithm>

using namespace std;

#pragma GCC target("avx2")
#pragma GCC optimization("O3")
#pragma GCC optimization("unroll-loops")

#define HASHMAP_CAPACITY 157
#define INPUT "linkedmap.in"
#define OUTPUT "linkedmap.out"

int hash_fnc(string s)
{
    long long hash_code = 0;
    if (hash_code == 0)
    {
        long long hash = 0, multiplier = 1;
        for (int i = s.size(); i >= 0; i--)
        {
            hash += s[i] * multiplier;
            long long shifted = multiplier << 5;
            multiplier = shifted - multiplier;
        }
        hash_code = hash;
    }
    return abs(hash_code % HASHMAP_CAPACITY);
}

template <typename K, typename V>
class hashmap_entry
{
protected:
    K *key;
    V *value;

public:
    hashmap_entry<K, V>(K *key, V *d)
    {
        this->key = key;
        value = d;
    }

    K *get_key()
    {
        return key;
    }

    V *get_value()
    {
        return value;
    }

    void set_value(V *new_value)
    {
        this->value = new_value;
    }
};

template <typename K, typename V, template <typename U, typename W> class link_entry>
class abstract_lincked_hashmap_entry : virtual public hashmap_entry<K, V>
{
public:
    link_entry<K, V> *next = NULL;
    link_entry<K, V> *prev = NULL;

    abstract_lincked_hashmap_entry<K, V, link_entry>(K *key, V *value,
                                                     link_entry<K, V> *next,
                                                     link_entry<K, V> *prev) : hashmap_entry<K, V>(key, value)
    {
        this->next = next;
        this->prev = prev;
    }

    abstract_lincked_hashmap_entry<K, V, link_entry>(link_entry<K, V> *next,
                                                     link_entry<K, V> *prev) : hashmap_entry<K, V>(NULL, NULL)
    {
        this->next = next;
        this->prev = prev;
    }
};

template <typename K, typename V>
class lincked_hashmap_entry : public abstract_lincked_hashmap_entry<K, V, lincked_hashmap_entry>
{
public:
    lincked_hashmap_entry(K *key, V *value,
                          lincked_hashmap_entry<K, V> *next,
                          lincked_hashmap_entry<K, V> *prev)
        : abstract_lincked_hashmap_entry<K, V, lincked_hashmap_entry>(key, value, next, prev), hashmap_entry<K, V>(key, value) {}
    lincked_hashmap_entry(K *key, V *value)
        : abstract_lincked_hashmap_entry<K, V, lincked_hashmap_entry>(key, value, NULL, NULL), hashmap_entry<K, V>(key, value) {}
};

template <typename K, typename V, template <typename U, typename W> class entry>
class hashmap_chain
{
private:
    int size_ = 0;
    list<entry<K, V> *> map_arr[HASHMAP_CAPACITY];

    typename list<entry<K, V> *>::iterator find(K key, int hash_code)
    {
        return find_if(map_arr[hash_code].begin(),
                       map_arr[hash_code].end(),
                       [key](entry<K, V> *val) { return *(val->get_key()) == key; });
    }

protected:
    entry<K, V> *get_entry(K key)
    {
        int hash_code = hash_fnc(key);
        typename list<entry<K, V> *>::iterator it = find(key, hash_code);
        if (it != map_arr[hash_code].end())
        {
            return *it;
        }
        else
        {
            return NULL;
        }
    }

public:
    entry<K, V> *put_chain(K key, V value)
    {
        K *static_key = new K(key);
        V *static_value = new V(value);
        int hash_code = hash_fnc(*static_key);
        typename list<entry<K, V> *>::iterator it = find(key, hash_code);
        if (it != map_arr[hash_code].end())
        {
            (*it)->set_value(static_value);
            delete static_key;
            return NULL;
        }
        size_++;
        entry<K, V> *new_entry = new entry<K, V>(static_key, static_value);
        map_arr[hash_code].push_back(new_entry);
        return new_entry;
    }

    V *get_chain(K key)
    {
        entry<K, V> *result = get_entry(key);
        if (result != NULL)
        {
            if (result->get_key() == NULL)
            {
                return NULL;
            }
            return result->get_value();
        }
        return NULL;
    }

    entry<K, V> *remove_chain(K key)
    {
        int hash_code = hash_fnc(key);
        typename list<entry<K, V> *>::iterator it = find(key, hash_code);
        if (it == map_arr[hash_code].end())
        {
            return NULL;
        }
        size_--;
        entry<K, V> *to_remove = *it;
        map_arr[hash_code].erase(it);
        return to_remove;
    }

    int size()
    {
        return this->size_;
    }
};

template <typename K, typename V, template <typename U, typename W> class entry_type>
class abstract_linked_hashmap : virtual public hashmap_chain<K, V, entry_type>
{
protected:
    entry_type<K, V> *head;
    entry_type<K, V> *tail;
    void insert_inner(entry_type<K, V> *val)
    {
        entry_type<K, V> *tail_preceder = this->tail->prev;
        tail->prev = val;
        tail_preceder->next = val;
        val->prev = tail_preceder;
        val->next = tail;
    }

    void remove_inner(entry_type<K, V> *val)
    {
        entry_type<K, V> *prev = val->prev;
        entry_type<K, V> *next = val->next;
        prev->next = next;
        next->prev = prev;
    }

public:
    abstract_linked_hashmap() : hashmap_chain<K, V, entry_type>()
    {
        this->head = new entry_type<K, V>(NULL, NULL, NULL, NULL);
        this->tail = new entry_type<K, V>(NULL, NULL, this->head, NULL);
        tail->prev = this->head;
    }

    void remove_lhm(K key)
    {
        entry_type<K, V> *to_remove = this->remove_chain(key);
        if (to_remove == NULL)
        {
            return;
        }
        remove_inner(to_remove);
    }

    void put_lhm(K key, V value)
    {
        entry_type<K, V> *new_entry = this->put_chain(key, value);
        if (new_entry == NULL)
        {
            return;
        }
        insert_inner(new_entry);
    }

    V *prev(K key)
    {
        entry_type<K, V> *result = this->get_entry(key);
        if (result != NULL && result->prev != NULL)
        {
            if (result->prev->get_key() == NULL)
            {
                return NULL;
            }
            return result->prev->get_value();
        }
        return NULL;
    }

    V *next(K key)
    {
        entry_type<K, V> *result = this->get_entry(key);
        if (result != NULL && result->next != NULL)
        {
            if (result->next->get_key() == NULL)
            {
                return NULL;
            }
            return result->next->get_value();
        }
        return NULL;
    }

    void remove_all_by_value(V value)
    {
        entry_type<K, V> *start = head->next;
        while (start->get_key() != NULL)
        {
            if (*(start->get_value()) == value)
            {
                auto to_remove = start;
                start = start->next;
                remove_inner(to_remove);
            }
        }
    }

    entry_type<K, V> *get_head()
    {
        return this->head;
    }

    entry_type<K, V> *get_tail()
    {
        return this->tail;
    }
};

template <typename K, typename V>
class linked_hashmap : public abstract_linked_hashmap<K, V, lincked_hashmap_entry>
{
public:
    void put(K key, V value)
    {
        this->put_lhm(key, value);
    }

    void remove(K key)
    {
        this->remove_lhm(key);
    }

    V *get(K key)
    {
        auto entry = this->get_entry(key);
        if (entry != NULL)
        {
            return entry->get_value();
        }
        else
        {
            return NULL;
        }
    }
};

template <typename V>
class linked_hashset : public linked_hashmap<V, V>
{
public:
    linked_hashset<V>()
    {
    }

    linked_hashset<V>(V *item)
    {
        this->put(*item, *item);
    }

    void insert(V key)
    {
        this->put(key, key);
    }
    bool exists(V key)
    {
        return this->get(key) != NULL;
    }

    auto get_danger(V key)
    {
        return this->get_entry(key);
    }

    void push_back(V val)
    {
        this->insert(val);
    }
};

template <typename K, typename V>
class multimap_entry : public hashmap_entry<K, linked_hashset<V>>
{
public:
    multimap_entry<K, V>(K *key, V *d) : hashmap_entry<K, linked_hashset<V>>(key, new linked_hashset<V>(d)) {}

    linked_hashset<V> *get_all_values()
    {
        return this->value;
    }

    void set_value(V *new_value)
    {
        this->value->push_back(*new_value);
    }

    void add_value(V new_value)
    {
        this->value->push_back(new_value);
    }
};

template <typename K, typename V>
class composite_entry : virtual public multimap_entry<K, V>, virtual public abstract_lincked_hashmap_entry<K, V, composite_entry>
{
public:
    composite_entry<K, V>(K *key, V *value,
                          composite_entry<K, V> *next,
                          composite_entry<K, V> *prev)
        : abstract_lincked_hashmap_entry<K, V, composite_entry>(next, prev),
          multimap_entry<K, V>(key, value),
          hashmap_entry<K, V>(key, value) {}
    composite_entry<K, V>(K *key, V *value)
        : multimap_entry<K, V>(key, value),
          abstract_lincked_hashmap_entry<K, V, composite_entry>(NULL, NULL),
          hashmap_entry<K, V>(key, value) {}
    using multimap_entry<K, V>::get_key;
    using multimap_entry<K, V>::set_value;
};

template <typename K, typename V, template <typename U, typename W> class entry_type>
class abstract_multihashmap : virtual public hashmap_chain<K, V, entry_type>
{
protected:
    lincked_hashmap_entry<V, V> *contains_value(V value, entry_type<K, V> *entry)
    {
        if (entry->get_all_values()->exists(value))
        {
            return entry->get_all_values()->get_danger(value);
        }
        return NULL;
    }

public:
    abstract_multihashmap() : hashmap_chain<K, V, entry_type>() {}

    linked_hashset<V> *get_mhm(K key)
    {
        entry_type<K, V> *result = this->get_entry(key);
        if (result == NULL)
        {
            return new linked_hashset<V>();
        }
        return result->get_all_values();
    }

    pair<linked_hashset<V> *, int> get_with_count(K key)
    {
        auto result = get_mhm(key);
        return {result, result->size()};
    }

    entry_type<K, V> *put_mhm(K key, V value)
    {
        entry_type<K, V> *key_entry = this->get_entry(key);
        if (key_entry == NULL)
        {
            return this->put_chain(key, value);
        }
        else if (contains_value(value, key_entry) == NULL)
        {
            key_entry->add_value(value);
        }
        return NULL;
    }

    entry_type<K, V> *remove_mhm(K key, V value)
    {
        entry_type<K, V> *key_entry = this->get_entry(key);
        if (key_entry != NULL)
        {
            auto result = contains_value(value, key_entry);
            if (result != NULL)
            {
                if (key_entry->get_all_values()->size() == 1)
                {
                    this->remove_chain(key);
                    return key_entry;
                }
                else
                {
                    key_entry->get_all_values()->remove(*(result->get_key()));
                }
            }
        }
        return NULL;
    }

    entry_type<K, V> *remove_all_mhm(K key)
    {
        return this->remove_chain(key);
    }
};

template <typename K, typename V>
class multi_hashmap : public abstract_multihashmap<K, V, multimap_entry>
{
public:
    auto get(K key)
    {
        return this->get_mhm(key);
    }

    auto get_withcount(K key)
    {
        return this->get_with_count(key);
    }

    void remove(K key, V value)
    {
        this->remove_mhm(key, value);
    }

    void remove_all(K key)
    {
        this->remove_all_mhm(key);
    }

    void put(K key, V value)
    {
        this->put_mhm(key, value);
    }
};

template <typename K, typename V>
class composite_hashmap : public abstract_multihashmap<K, V, composite_entry>,
                          public abstract_linked_hashmap<K, V, composite_entry>
{
public:
    composite_hashmap<K, V>()
        : abstract_multihashmap<K, V, composite_entry>(), abstract_linked_hashmap<K, V, composite_entry>() {}

    using abstract_linked_hashmap<K, V, composite_entry>::prev;
    using abstract_linked_hashmap<K, V, composite_entry>::next;

    auto get(K key)
    {
        return this->get_mhm(key);
    }

    auto get_and_unwrap(K key)
    {
        auto butch = this->get_mhm(key);
        if (butch->size() == 1)
        {
            return butch.front();
        }
        else
        {
            return NULL;
        }
    }

    auto get_withcount(K key)
    {
        return this->get_with_count(key);
    }

    void remove(K key, V value)
    {
        auto to_remove = this->remove_mhm(key, value);
        if (to_remove != NULL)
        {
            this->remove_inner(to_remove);
        }
    }

    void remove_all(K key)
    {
        auto to_remove = this->remove_all_mhm(key);
        if (to_remove != NULL)
        {
            this->remove_inner(to_remove);
        }
    }

    void put(K key, V value)
    {
        auto to_isert = this->put_mhm(key, value);
        if (to_isert != NULL)
        {
            this->insert_inner(to_isert);
        }
    }
};

string
null_handler(string *ref)
{
    if (ref == NULL)
    {
        return "none";
    }
    return *ref;
}

// int main()
// {
//     ios_base::sync_with_stdio(false);
//     cin.tie(NULL);
//     freopen(INPUT, "r", stdin);
//     freopen(OUTPUT, "w", stdout);
//     string operation;
//     string key;
//     string value;
//     multi_hashmap<string, string> map = multi_hashmap<string, string>();
//     string resultt = "";
//     while (cin >> operation)
//     {
//         cin >> key;
//         if (operation == "put")
//         {
//             cin >> value;
//             map.put(key, value);
//         }
//         else if (operation == "get")
//         {
//             auto result = map.get_with_count(key);
//             cout << result.second << " ";
//             lincked_hashmap_entry<string, string> *begin = result.first->get_head();
//             begin = begin->next;
//             while (begin != NULL && begin->get_key() != NULL)
//             {
//                 cout << *(begin->get_key()) << " ";
//                 begin = begin->next;
//             }
//             cout << "\n";
//         }
//         else if (operation == "delete")
//         {
//             cin >> value;
//             map.remove(key, value);
//         }
//         else if (operation == "deleteall")
//         {
//             map.remove_all(key);
//         }
//     }
//     return 0;
// }

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    freopen(INPUT, "r", stdin);
    freopen(OUTPUT, "w", stdout);
    string operation;
    string key;
    string value;
    linked_hashmap<string, string> map = linked_hashmap<string, string>();
    while (cin >> operation)
    {
        cin >> key;
        if (operation == "put")
        {
            cin >> value;
            map.put(key, value);
        }
        else if (operation == "get")
        {
            cout << null_handler(map.get(key)) << "\n";
        }
        else if (operation == "delete")
        {
            map.remove(key);
        }
        else if (operation == "prev")
        {
            cout << null_handler(map.prev(key)) << "\n";
        }
        else if (operation == "next")
        {
            cout << null_handler(map.next(key)) << "\n";
        }
    }
    return 0;
}