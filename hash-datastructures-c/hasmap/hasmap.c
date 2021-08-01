#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define VECTOR_INIT_SIZE 997
#define WORD_MAX_SIZE 25
#define VECTOR_INT_INIT_SIZE 0

/* utills */

struct data
{
    char *key;
    char *value;
    struct data *next;
};

struct data *make_data_entry(char *key, char *value, struct data *next)
{
    struct data *entry = (struct data *)malloc(sizeof(struct data));
    entry->key = key;
    entry->value = value;
    entry->next = next;
    return entry;
}

int hash(char *s)
{
    const int p = 31;
    const int m = 1e9 + 9;
    long long hash_value = 0;
    long long p_pow = 1;
    for (int i = 0; i < strlen(s); i++)
    {
        char c = s[i];
        hash_value = (hash_value + (c - 'a' + 1) * p_pow) % m;
        p_pow = (p_pow * p) % m;
    }
    return hash_value % VECTOR_INIT_SIZE;
}

struct my_simple_vector
{
    struct data **arr;
    int size;
    int arr_size;
};

struct data **make_data_arr()
{
    struct data **arr = (struct data **)malloc(VECTOR_INIT_SIZE * sizeof(struct data *));
    for (int i = 0; i < VECTOR_INIT_SIZE; i++)
    {
        char *key = (char *)malloc(WORD_MAX_SIZE * sizeof(char));
        key[0] = '\0';
        char *value = (char *)malloc(WORD_MAX_SIZE * sizeof(char));
        value[0] = '\0';
        arr[i] = make_data_entry(key, value, NULL);
        if (i != 0)
        {
            arr[i - 1]->next = arr[i];
        }
    }
    return arr;
}

void init(struct my_simple_vector *vector)
{
    (*vector).arr = make_data_arr();
    (*vector).arr_size = VECTOR_INIT_SIZE;
    (*vector).size = 0;
}

void expand_vector(struct my_simple_vector *vector)
{
    (*vector).arr = realloc((*vector).arr, (*vector).arr_size * 2 * sizeof(struct data));
    (*vector).arr_size = (*vector).arr_size * 2;
    for (int i = (*vector).arr_size / 2; i < (*vector).arr_size; i++)
    {
        (*vector).arr[i] = '\0';
    }
}

void vector_insert(struct my_simple_vector *vector, struct data *item)
{
    if ((*vector).size == (*vector).arr_size)
    {
        expand_vector(vector);
    }
    *((*vector).arr + (*vector).size++) = item;
}

struct data *vector_get(struct my_simple_vector *vector, int pos)
{
    return vector->arr[pos];
}

void vector_set(struct my_simple_vector *vector, int pos, struct data *value)
{
    if (pos != 0)
    {
        vector->arr[pos - 1]->next = value;
    }
    value->next = vector->arr[pos]->next;
    free(vector->arr[pos]);
    vector->arr[pos] = value;
}

/* hashmap  open*/

struct hashmap
{
    struct my_simple_vector *array;
    int capacity;
};

struct hashmap *make_hash_map()
{
    static struct hashmap map;
    static struct my_simple_vector array;
    init(&array);
    map.array = &array;
    map.capacity = array.size;
    return &map;
}

char *map_get(struct hashmap *map, char *key)
{
    int hash_code = hash(key);
    if (*(vector_get(map->array, hash_code)->key) == '\0')
    {
        return NULL;
    }
    else
    {
        struct data *entry = vector_get(map->array, hash_code);
        while (entry != NULL)
        {
            if (!strcmp(entry->key, key))
            {
                return entry->value;
            }
            entry = entry->next;
        }
        return NULL;
    }
}

int map_remove(struct hashmap *map, char *key)
{
    int hash_code = hash(key);
    if (*(vector_get(map->array, hash_code)->key) == '\0')
    {
        return 0;
    }
    else
    {
        struct data *prev = NULL;
        struct data *current = vector_get(map->array, hash_code);
        while (current != NULL)
        {
            if (!strcmp(current->key, key))
            {
                if (prev == NULL)
                {
                    vector_set(map->array, hash_code, vector_get(map->array, hash_code)->next);
                    return 1;
                }
                else
                {
                    prev->next = current->next;
                    return 1;
                }
            }
            prev = current;
            current = current->next;
        }
    }
    return 0;
}

int map_put(struct hashmap *map, char *key, char *data)
{
    int hash_code = hash(key);

    struct data *entry = make_data_entry(key, data, NULL);

    if (*(vector_get(map->array, hash_code)->key) == '\0')
    {
        vector_set(map->array, hash_code, entry);
    }
    else
    {
        struct data *prev = NULL;
        struct data *current = vector_get(map->array, hash_code);

        while (current != NULL)
        {
            if (!strcmp(current->key, key))
            {
                if (prev == NULL)
                {
                    entry->next = current->next;
                    vector_set(map->array, hash_code, entry);
                    return 1;
                }
                else
                {
                    entry->next = current->next;
                    prev->next = entry;
                    return 1;
                }
            }
            prev = current;
            current = current->next;
        }
        prev->next = entry;
    }
    return 0;
}

/* simple int vector */

struct my_simple_vector_str
{
    char **arr;
    int size;
    int arr_size;
};

void init_vector_str(struct my_simple_vector_str *vector)
{
    vector->arr = (char **)malloc(VECTOR_INT_INIT_SIZE * sizeof(char *));
    (*vector).arr_size = VECTOR_INT_INIT_SIZE;
    (*vector).size = 0;
}

void expand_vector_str(struct my_simple_vector_str *vector)
{
    (*vector).arr = realloc((*vector).arr, ((*vector).arr_size + 1) * 2 * sizeof(char));
    (*vector).arr_size = ((*vector).arr_size + 1) * 2;
}

void vector_str_insert(struct my_simple_vector_str *vector, char *item)
{
    if ((*vector).size == (*vector).arr_size)
    {
        expand_vector_str(vector);
    }
    *((*vector).arr + (*vector).size++) = item;
}

void vector_str_remove_last(struct my_simple_vector_str *vector)
{
    vector->size--;
}

/* hashmap chain */

struct hashmap_chain
{
    struct my_simple_vector_str **array;
    int capacity;
};

struct hashmap_chain *make_hash_map_chain()
{
    static struct hashmap_chain map;
    map.array = (struct my_simple_vector_str **)malloc(VECTOR_INIT_SIZE * sizeof(struct my_simple_vector_str **));
    for (int i = 0; i < VECTOR_INIT_SIZE; i++)
    {
        struct my_simple_vector_str *tmp = (struct my_simple_vector_str *)malloc(VECTOR_INT_INIT_SIZE * sizeof(struct my_simple_vector_str *));
        init_vector_str(tmp);
        map.array[i] = tmp;
    }
    map.capacity = VECTOR_INIT_SIZE;
    return &map;
}

char *map_get_chain(struct hashmap_chain *map, char *key)
{
    int hash_code = hash(key);
    if (map->array[hash_code]->size == 0)
    {
        return NULL;
    }
    else
    {
        return map->array[hash_code]->arr[map->array[hash_code]->size - 1];
    }
}

int map_remove_chain(struct hashmap_chain *map, char *key)
{
    int hash_code = hash(key);
    vector_str_remove_last(map->array[hash_code]);
    return 1;
}

int map_put_chain(struct hashmap_chain *map, char *key, char *data)
{
    int hash_code = hash(key);
    vector_str_insert(map->array[hash_code], data);
    return 1;
}

/* global */

char *put_str = "put";
char *get_str = "get";
char *delete_str = "delete";

/* read utills */

struct query
{
    char *operation;
    char *key;
    char *value;
};

char *get_operation(char *line, int max_operation_len)
{
    if (!strncmp(line, put_str, strlen(put_str)))
    {
        return put_str;
    }
    else if (!strncmp(line, get_str, strlen(get_str)))
    {
        return get_str;
    }
    else if (!strncmp(line, delete_str, strlen(delete_str)))
    {
        return delete_str;
    }
    exit(1);
}

void dump_linebreak(char *line)
{
    if (strlen(line) != 0)
    {
        line[strlen(line) - 1] = '\0';
    }
}

char *get_word(char *line, int max_word_len, int from)
{

    char *buffer = (char *)malloc(max_word_len * sizeof(char));
    int j = 0;
    for (int i = from; i < max_word_len && line[i] != '\0' && line[i] != ' '; i++, j++)
    {
        buffer[j] = line[i];
    }

    buffer[j] = '\0';

    char *word = (char *)malloc((strlen(buffer) + 5) * sizeof(char));
    strcpy(word, buffer);
    free(buffer);
    return word;
}

struct query *make_query(char *line)
{
    struct query *q = (struct query *)malloc(sizeof(struct query));
    char *operation = get_operation(line, strlen(delete_str));
    q->operation = operation;
    q->key = get_word(line, WORD_MAX_SIZE, strlen(operation) + 1);
    if (!strcmp(operation, put_str))
    {
        q->value = get_word(line, WORD_MAX_SIZE, strlen(operation) + 1 + strlen(q->key) + 1);
    }
    else
    {
        q->value = NULL;
    }
    return q;
}

struct query *next_query(FILE *fp)
{
    char chunk[WORD_MAX_SIZE * 3];
    if (fgets(chunk, sizeof(chunk), fp) == NULL)
    {
        return NULL;
    }
    dump_linebreak(chunk);
    return make_query(chunk);
}

/* main */

int main(void)
{
    char result[100000];
    char *none_str = "none\n";
    FILE *fp = fopen("map.in", "r");
    if (fp == NULL)
    {
        perror("Unable to open file");
        exit(1);
    }
    FILE *out = fopen("map.out", "w");
    if (out == NULL)
    {
        perror("Unable to open file");
        exit(1);
    }
    struct hashmap *map = make_hash_map();
    struct query *q = next_query(fp);
    while (q != NULL)
    {
        if (!strcmp(q->operation, put_str))
        {
            map_put(map, q->key, q->value);
        }
        else if (!strcmp(q->operation, get_str))
        {
            char *res = map_get(map, q->key);
            strcat(result, res != NULL ? strcat(res, "\n") : none_str);
        }
        else if (!strcmp(q->operation, delete_str))
        {
            map_remove(map, q->key);
        }
        free(q);
        q = next_query(fp);
    }

    fputs(result, out);

    fclose(fp);
    fclose(out);
    return 0;
}
