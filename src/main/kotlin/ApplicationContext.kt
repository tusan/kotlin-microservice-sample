import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicInteger

val applicationContext = org.koin.dsl.module.applicationContext {
    bean { ItemRepository() }
    bean { AtomicInteger() }
    bean { CopyOnWriteArrayList<Item>() }
}